package learning.spring.boot.rest.webservices.user.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post2 extends RepresentationModel<Post2> {

	@Id
	@GeneratedValue
	private Integer postId;

	@Size(min = 3, message = "Minimum length of a post is 3")
	private String postName;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User2 user;
	
	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}

	public Post2() {
		super();
	}

	public Post2(Integer postId, String postName) {
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
