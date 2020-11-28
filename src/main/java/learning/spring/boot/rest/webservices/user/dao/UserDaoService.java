package learning.spring.boot.rest.webservices.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import learning.spring.boot.rest.webservices.exception.UserAdditionNotAllowedException;
import learning.spring.boot.rest.webservices.user.model.User;

@Repository
public class UserDaoService {

	private static List<User> userList = new ArrayList<User>();

	private static int userIdCounter = 0;

	private static final int MAX_USER = 4;

	static {
		userList.add(new User(++userIdCounter, "Romit", new Date()));
		userList.add(new User(++userIdCounter, "Rajat", new Date()));
		userList.add(new User(++userIdCounter, "Tripti", new Date()));
	}

	public List<User> getAllUsers() {
		return userList;
	}

	public User reteriveUser(int id) {
		User user = null;
		for (User currentUser : userList) {
			if (currentUser.getId() == id) {
				user = currentUser;
				break;
			}
		}
		return user;
	}

	public User saveUser(User newUser) {
		if (userList.size() == MAX_USER) {
			throw new UserAdditionNotAllowedException(
			        "Maximum number of user are registered. no more new registeration is allow at this point in time.");
		}
		if (newUser.getId() == null) {
			newUser.setId(++userIdCounter);
		}
		userList.add(newUser);

		return newUser;
	}

	public User deleteUser(int id) {
		Iterator<User> iterator = userList.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
