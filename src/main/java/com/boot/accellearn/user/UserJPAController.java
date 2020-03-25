package com.boot.accellearn.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User createdUser = userRepository.save(user);
		
		//Tez notes: As per HTTP standards, we would like to return
		//the URI of the new resource created.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(createdUser.getId().toString())
			.toUri();
		//Tez notes:
		//HTTP response header would contain the URI in "location" key.
		//HTTP response status would be 201 because of the created method usage.
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable Integer id) {
		Optional<User> foundUser = userRepository.findById(id);
		if(!foundUser.isPresent()) {
			throw new UserNotFoundException("user-"+id);
		}
		
		//We will not return just the plain User object found but will also
		//add some more relevant information/links in the response
		Resource<User> userResource = new Resource<User>(foundUser.get());
		//add the URI link of a method to the resource to be returned
		userResource.add(
				//get the link to URI of getUsers method on this class.
				linkTo(
						methodOn(getClass()).getUsers()
				)
				//The URI link being returned in response will be referred
				//with key 'all-users'
				.withRel("all-users"));
		return userResource;
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void removeUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getUserPosts(@PathVariable Integer id) {
		Optional<User> foundUser = userRepository.findById(id);
		if(!foundUser.isPresent()) {
			throw new UserNotFoundException("user-"+id);
		}		
		return foundUser.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> addPost(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> foundUser = userRepository.findById(id);
		if(!foundUser.isPresent()) {
			throw new UserNotFoundException("user-"+id);
		}
		
		post.setUser(foundUser.get());
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
}
