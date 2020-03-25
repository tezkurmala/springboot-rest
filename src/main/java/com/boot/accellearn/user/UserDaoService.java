package com.boot.accellearn.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User(1, "One", new Date()));
		users.add(new User(2, "Two", new Date()));
		users.add(new User(3, "Three", new Date()));
	}
	public List<User> findAll(){
		return users;
	}

	public User save(User user){
		if(user.getId() == null)
			user.setId(users.size() + 1);
		users.add(user);
		return user;
	}
	
	public User findById(Integer id) {
		for(User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public User deleteById(Integer id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId().equals(id)) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
