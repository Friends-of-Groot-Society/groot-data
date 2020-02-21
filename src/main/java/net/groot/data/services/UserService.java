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
        user.setIsbn(userRequest.getIsbn());
        user.setAuthor(userRequest.getAuthor());
        user.setTitle(userRequest.getTitle());
        user.setName(userRequest.getName());
        user.setType(userRequest.getType());

        user = userRepository.save(user);

        return user.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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

        if (userFromDatabase.empty() != null) { //.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id: '%s' not found", id));
        }

        User userToUpdate = userFromDatabase.get();

        userToUpdate.setAuthor(userToUpdateRequest.getAuthor());
        userToUpdate.setIsbn(userToUpdateRequest.getIsbn());
        userToUpdate.setTitle(userToUpdateRequest.getTitle());
        userToUpdate.setName(userToUpdateRequest.getName());
        userToUpdate.setType(userToUpdateRequest.getType());

        return userToUpdate;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
