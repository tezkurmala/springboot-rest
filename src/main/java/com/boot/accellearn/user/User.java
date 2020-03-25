package com.boot.accellearn.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//Allows modify the default swagger generated docs.
@ApiModel(description = "User entity for Social media app.")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	//Validator API. Spring auto checks the size of input data from body.
	@Size(min = 2, message = "Name does not have expected minimum characters")
	@ApiModelProperty(notes = "Name has to contain atleast 2 characters.") //Swagger customization
	private String name;
	@Past //Validator API. Spring auto checks the input data.
	@ApiModelProperty(notes = "Date has to be of past.") //Swagger customization
	private Date dateOfBirth;
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
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
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public User() {
		super();
	}
	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}	
}
