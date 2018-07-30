package com.ericsson.bss.ms.webservices.post.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ericsson.bss.ms.webservices.post.user.exceptions.UserNotFoundException;

@RestController
public class UserResource {
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping("/users")
	List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@PostMapping("/users")
	ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/users/{id}")
	Resource<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("User not found");
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = 
				 ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUser(id));
		resource.add(linkTo.withRel("user"));
		return resource;
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
	}
}
