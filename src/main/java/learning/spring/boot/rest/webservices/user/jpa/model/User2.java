package learning.spring.boot.rest.webservices.user.jpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User Model description")
@Entity
public class User2 extends RepresentationModel<User2> {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, message = "Name must have atleast 2 character.")
	@ApiModelProperty(value = "Value of name must be of atleast size 2")
	private String name;

	@PastOrPresent
	@ApiModelProperty(value = "Date of birth can't be a futre date. it must be of past or present.")
	private Date dateOfBirth;

	//By default data fetch type is eager, thus when user details are source it will also source post info
	@OneToMany(mappedBy = "user")
	private List<Post2> postList;

	protected User2() {

	}

	public User2(Long id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public List<Post2> getPostList() {
		return postList;
	}

	public void setPostList(List<Post2> postList) {
		this.postList = postList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
