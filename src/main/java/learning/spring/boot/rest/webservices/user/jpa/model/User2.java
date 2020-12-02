package learning.spring.boot.rest.webservices.user.jpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Integer id;

	@Size(min = 2, message = "Name must have atleast 2 character.")
	@ApiModelProperty(value = "Value of name must be of atleast size 2")
	private String name;

	@PastOrPresent
	@ApiModelProperty(value = "Date of birth can't be a futre date. it must be of past or present.")
	private Date dateOfBirth;
	
	protected User2() {
		
	}

	public User2(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
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
