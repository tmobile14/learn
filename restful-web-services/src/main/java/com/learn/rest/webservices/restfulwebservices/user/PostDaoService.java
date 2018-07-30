package com.learn.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {
	private Map<Integer,List<Post>> userPostsMap = new HashMap<Integer,List<Post>>();
	
	public Post addPost(Integer userId, Post post) {
		if(userPostsMap.get(userId) == null) {
			List<Post> listPost = new ArrayList<Post>();
			listPost.add(post);
			userPostsMap.put(userId, listPost);
		} else {
			userPostsMap.get(userId).add(post);
		}
		
		return post;
	}
	
	public List<Post> findAllPosts(Integer userId) {
		return userPostsMap.get(userId);
	}
}
