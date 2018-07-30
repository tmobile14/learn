package com.ericsson.bss.ms.webservices.post.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	@Autowired
	UserRepository userDaoService;

	@Autowired
	PostRepository postService;

	@GetMapping("/jpa/users")
	List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@PostMapping("/jpa/users")
	ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{id}")
	Resource<User> retrieveUser(@PathVariable int id) {
		 Optional<User> user = userDaoService.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = 
				 ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUser(id));
		resource.add(linkTo.withRel("user"));
		return resource;
	}

	@DeleteMapping("/jpa/users/{id}")
	void deleteUser(@PathVariable int id) {
		userDaoService.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<User> user = userDaoService.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		return user.get().getPostList();
	}

	@PostMapping("/jpa/users/{id}/posts")
	ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userDaoService.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		post.setUser(user.get());
		postService.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
