package chandra.prakash.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import chandra.prakash.registration.dto.UserDTO;
import chandra.prakash.registration.dto.UserLoginDTO;
import chandra.prakash.registration.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{userID}")
	public UserDTO getUserByID(
			@PathVariable Long userID) {
		return userService.getUserByID(userID);
	}
	
	@PostMapping("/users")
	public UserDTO addNewUser(
			@RequestBody @Valid UserDTO userDTO) {
		return userService.addNewUser(userDTO);
	}
	
	@PostMapping("/users/login")
	public UserDTO getClientLogin(
			@RequestBody @Valid UserLoginDTO userLoginDTO) {
		return userService.getUserLogin(userLoginDTO);
	}
	
    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }
    
    @GetMapping(value = "/")
    public String index(){
        return "Hello world";
    }
}
