package com.ericsson.bss.ms.webservices.post.user;

import java.util.ArrayList;
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

@ApiModel(description="Description for the user")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="name shoudl have at least 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Date cannot be in the past")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	List<Post> postList = new ArrayList<Post>();

	public User() {
		super();
	}

	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public List<Post> getPostList() {
		return postList;
	}


	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	
	
}
