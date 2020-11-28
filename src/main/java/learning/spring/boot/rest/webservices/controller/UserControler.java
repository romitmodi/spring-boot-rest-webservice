package learning.spring.boot.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import learning.spring.boot.rest.webservices.exception.PostAlreadyAssignedException;
import learning.spring.boot.rest.webservices.exception.PostNotAssignedException;
import learning.spring.boot.rest.webservices.exception.PostNotFoundException;
import learning.spring.boot.rest.webservices.exception.UserNotFoundException;
import learning.spring.boot.rest.webservices.user.dao.PostDaoService;
import learning.spring.boot.rest.webservices.user.dao.UserDaoService;
import learning.spring.boot.rest.webservices.user.model.Post;
import learning.spring.boot.rest.webservices.user.model.User;

@RestController
public class UserControler {

	@Autowired
	private UserDaoService userService;

	@Autowired
	private PostDaoService postService;

	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@GetMapping(path = "/users/{id}")
	public User retriveUser(@PathVariable int id) {
		User user = userService.reteriveUser(id);
		if (user == null) {
			throw new UserNotFoundException("id=" + id);
		}
		Link link = new Link(linkTo(methodOn(this.getClass()).getAllUser()).withSelfRel().getHref());
		user.add(link);
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User newUser = userService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
		        .toUri();
		// return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		return ResponseEntity.created(uri).body(newUser);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User deletedUser = userService.deleteUser(id);
		if (deletedUser == null) {
			throw new UserNotFoundException("user with id= not found" + id);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> getAllUserPost(@PathVariable int id) {
		User user = userService.reteriveUser(id);
		if (user == null) {
			throw new UserNotFoundException("User with id " + id + " not found. Please pass correct userId.");
		}
		return user.getPostList();
	}

	@GetMapping("/users/{id}/posts/{postId}")
	public Post retriveUserPost(@PathVariable int id, @PathVariable int postId) {
		User user = userService.reteriveUser(id);
		if (user == null) {
			throw new UserNotFoundException("User with id " + id + " not found. Please check userId.");
		}
		Post post = postService.retrivePost(postId, user.getPostList());
		if (post == null) {
			if (postService.isPostExists(postId)) {
				throw new PostNotAssignedException(
				        "Post with PostId " + postId + " is not assigned to user. Please check userPostId.");
			}
			throw new PostNotFoundException("Post with PostId " + postId
			        + " is not found to user. Please check userPostId or create a new post and assigned it to user.");
		}
		return post;
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<User> saveUserPost(@PathVariable int id, @Valid @RequestBody Post post) {
		User user = userService.reteriveUser(id);
		if (user == null) {
			throw new UserNotFoundException("User with id " + id + " not found. Please check userId.");
		}

		Post postTemp = postService.retrivePost(id, user.getPostList());
		if (postTemp != null) {
			throw new PostAlreadyAssignedException(
			        "Post with id " + postTemp.getPostId() + " already assigned to current user.");
		}

		postTemp = postService.addPost(post);
		user.getPostList().add(postTemp);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/users/{id}/posts/{postId}")
		        .buildAndExpand(id, postTemp.getPostId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
}
