package learning.spring.boot.rest.webservices.user.model;

import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

public class Post extends RepresentationModel<Post> {

	private Integer postId;

	@Size(min = 3, message = "Minimum length of a post is 3")
	private String postName;

	public Post(Integer postId, String postName) {
		super();
		this.postId = postId;
		this.postName = postName;
	}

	public Integer getPostId() {
		return postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}
}
