package net.groot.data.controllers; 

 
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
 
import net.groot.data.entities.User;
import net.groot.data.requests.UserRequest;
import net.groot.data.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
 
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> createNewUser(@Valid @RequestBody UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) {
        Long primaryKey = userService.createNewUser(userRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(primaryKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
//	@PostMapping("/authenticate")
//	public boolean loginCustomer(@RequestBody User user, HttpSession httpSession) {
//		
////		String username = httpSession.getAttribute(em).toString();
//		
//		if(userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword()) != null) {
//			return true;
//		}
//		else {
//			return false;
//		}
//		
//	} 
 
	@GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    
}

