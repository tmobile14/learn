package com.learn.rest.webservices.restfulwebservices.user;

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

import com.learn.rest.webservices.restfulwebservices.exception.PostsNotFoundException;
import com.learn.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostDaoService postService;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("all-users"));
		
		return resource;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/jpa/users") 
	ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts") 
	List<Post> getPost(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts") 
	ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	
/*	@PostMapping("/jpa/users/write/{filename}/{str}") 
	void write(@PathVariable String filename, @PathVariable String str) {
		service.writeFile(filename, str);
	}

	@GetMapping("/jpa/users/read/{filename}") 
	String read(@PathVariable String filename) {
		return service.readFile(filename);
	}*/

}
