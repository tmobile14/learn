package com.learn.rest.webservices.restfulwebservices.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.rest.webservices.restfulwebservices.exception.InvalidInputException;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Ashish", new Date()));
		users.add(new User(3, "Anjali", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		} else {
			for(User u: users) {
				if(u.getId() == user.getId()) {
					throw new InvalidInputException("User id already exist");
				}
			}
		}
		
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		
		return null;
		
	}

	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}

	public void writeFile(String filename, String str) {
		try {
			File file = new File("/opt/ashish/" + filename);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(str);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile(String filename) {
		String data = "";
	    try {
			data = new String(Files.readAllBytes(Paths.get("/opt/ashish/" + filename)));
		} catch (IOException e) {
			e.printStackTrace();
			return "No such file exception";
		}
	    return data;
	}

}
