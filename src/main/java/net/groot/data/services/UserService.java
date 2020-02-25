package net.groot.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.groot.data.entities.User;
import net.groot.data.notfound.UserNotFoundException;
import net.groot.data.requests.UserRequest;

@Service
public class UserService {

	@Autowired
	private net.groot.data.repositories.UserRepository userRepository;

	public Long createNewUser(UserRequest userRequest) {
		User user = new User();
 
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setfName(userRequest.getfName());
		user.setlName(userRequest.getlName());
		user.setMemberSince(userRequest.getMemberSince());
		user.setGroupType(userRequest.getGroupType());
		user.setMedia(userRequest.getMedia());

		user = userRepository.save(user);

		return user.getId();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByEmailAndPassword(String email, String password) {
		Optional<User> requestedUser = userRepository.findByEmailAndPassword(email, password);
		if (requestedUser.empty() == null) {
			throw new UserNotFoundException(String.format("User with email: '%s' not found", email));
		}
		
		return requestedUser.get();
	};

//	public User getUserByEmail(String email) {
//		Optional<User> requestedUser = userRepository.findByEmail(email);
//		if (requestedUser.empty() == null) {
//			throw new UserNotFoundException(String.format("User with email: '%s' not found", email));
//		} 
//		return requestedUser.get();
//	}
	 
	public User getUserByEmail(String email) {
		Optional<User> requestedUser = userRepository.findByEmail(email);
		try {
			return requestedUser.get(); 
		} catch (Exception e) {
			return null;
		}
	}
	
	public User getUserById(Long id) {
		Optional<User> requestedUser = userRepository.findById(id);

		if (requestedUser.empty() == null) {
			throw new UserNotFoundException(String.format("User with id: '%s' not found", id));
		}

		return requestedUser.get();
	}


	@Transactional
	public User updateUser(Long id, UserRequest userToUpdateRequest) {

		Optional<User> userFromDatabase = userRepository.findById(id);

		if (userFromDatabase.empty() != null) { // .isEmpty()) {
			throw new UserNotFoundException(String.format("User with id: '%s' not found", id));
		}

		User userToUpdate = userFromDatabase.get();

		userToUpdate.setEmail(userToUpdateRequest.getEmail());
		userToUpdate.setPassword(userToUpdateRequest.getPassword());
		userToUpdate.setfName(userToUpdateRequest.getfName());
		userToUpdate.setlName(userToUpdateRequest.getlName());
		userToUpdate.setMemberSince(userToUpdateRequest.getMemberSince());
		userToUpdate.setGroupType(userToUpdateRequest.getGroupType());
		userToUpdate.setMedia(userToUpdateRequest.getMedia());

		return userToUpdate;
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
