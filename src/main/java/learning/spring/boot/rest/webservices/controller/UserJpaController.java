package learning.spring.boot.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import learning.spring.boot.rest.webservices.user.dao.Post2Repository;
import learning.spring.boot.rest.webservices.user.dao.User2Repository;
import learning.spring.boot.rest.webservices.user.jpa.model.Post2;
import learning.spring.boot.rest.webservices.user.jpa.model.User2;
import learning.spring.boot.rest.webservices.user.model.Post;

@RestController
public class UserJpaController {

	@Autowired
	private User2Repository user2JRepository;

	@Autowired
	private Post2Repository post2Repository;

	@GetMapping(path = "/jpa/users")
	public List<User2> getAllUser() {
		return user2JRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public User2 retriveUser(@PathVariable long id) {
		Optional<User2> user = user2JRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id=" + id);
		}
		User2 user2 = user.get();
		Link link = new Link(linkTo(methodOn(this.getClass()).getAllUser()).withSelfRel().getHref());
		user2.add(link);
		return user2;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User2 user) {
		User2 newUser = user2JRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newUser.getId()).toUri();
		// return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		return ResponseEntity.created(uri).body(newUser);
	}

	@DeleteMapping("/jpa/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable long id) {
		user2JRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post2> getAllUserPost(@PathVariable int id) {
		User2 user = this.findUser(id);
		return user.getPostList();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User2> saveUserPost(@PathVariable Long id, @Valid @RequestBody Post2 post) {
		User2 user = this.findUser(id);
		post.setUser(user);
		Post2 postTemp = post2Repository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/jpa/users/{id}/posts/{postId}")
		        .buildAndExpand(id, postTemp.getPostId()).toUri();

		return ResponseEntity.created(uri).body(user);
	}

	@GetMapping("/jpa/users/{id}/posts/{postId}")
	public Post2 retriveUserPost(@PathVariable int id, @PathVariable int postId) {
		User2 user = this.findUser(id);
		Optional<Post2> post = post2Repository.findById(postId);
		if (!post.isPresent() || user.getId()!=post.get().getUser().getId()) {
			throw new PostNotFoundException("Post with PostId " + postId
			        + " is not found to user. Please check userPostId or create a new post and assigned it to user.");
		}
		return post.get();
	}

	private User2 findUser(long id) {
		Optional<User2> user = user2JRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User with id " + id + " not found. Please check userId.");
		}

		return user.get();
	}

}
