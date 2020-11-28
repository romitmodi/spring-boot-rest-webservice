package learning.spring.boot.rest.webservices.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

public class User extends RepresentationModel<User> {

	private Integer id;

	@Size(min = 2, message = "Name must have atleast 2 character.")
	private String name;

	@PastOrPresent
	private Date dateOfBirth;

	private List<Post> postList;

	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		postList = new ArrayList<Post>();
	}

	public List<Post> getPostList() {
		return postList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
