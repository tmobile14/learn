package com.learn.rest.webservices.restfulwebservices.user;

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

import com.learn.rest.webservices.restfulwebservices.exception.PostsNotFoundException;
import com.learn.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;

	@Autowired
	private PostDaoService postService;

	@GetMapping("/users")
	List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("all-users"));
		
		return resource;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}

	@PostMapping("/users") 
	ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PostMapping("/users/{id}/posts") 
	ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Post savedPost = postService.addPost(id, post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postid}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}

	@GetMapping("/users/{id}/posts") 
	List<Post> getPost(@PathVariable int id) {
		System.out.println("Inside getPost");
		List<Post> savedPosts = postService.findAllPosts(id);
		if(savedPosts != null) {
			System.out.println("Found Posts: " + savedPosts.size());
		} else {
			System.out.println("No posts found");
			throw new PostsNotFoundException("No posts found");
		}
		return savedPosts;
	}

	
/*	@PostMapping("/users/write/{filename}/{str}") 
	void write(@PathVariable String filename, @PathVariable String str) {
		service.writeFile(filename, str);
	}

	@GetMapping("/users/read/{filename}") 
	String read(@PathVariable String filename) {
		return service.readFile(filename);
	}*/

}
