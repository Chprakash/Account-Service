package chandra.prakash.registration.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import chandra.prakash.registration.dto.UserDTO;
import chandra.prakash.registration.entity.User;
import chandra.prakash.registration.repository.UserRepository;
import chandra.prakash.registration.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private User userDTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailID(userDTO.getEmailID());
		user.setPassword(userDTO.getPassword());
		user.setStatus(userDTO.getStatus());
		return user;
	}
	
	private UserDTO clientToClinetDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmailID(user.getEmailID());
		userDTO.setPassword(user.getPassword());
		userDTO.setStatus(user.getStatus());
		return userDTO;
	}

	@Override
	public UserDTO getUserByID(Long id) {
		User user = userRepository.getOne(id);
		System.out.println(bCryptPasswordEncoder.encode(user.getPassword()));
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmailID(user.getEmailID());
		return userDTO;
	}

	@Override
	public UserDTO addNewUser(UserDTO userDTO) {
		User user = userDTOToUser(userDTO);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		userDTO = clientToClinetDTO(userRepository.save(user));
		if(userDTO != null) {
			userDTO.setPassword("");
		}
		return userDTO;
	}
	

	@Override
	public UserDTO getUserLogin(UserDTO userDTO) {
		Boolean validUser = false;
		User user = userRepository.getUserByEmailID(userDTO.getEmailID());
		if(user != null) {
			validUser = bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
		}
		userDTO = clientToClinetDTO(user);
		if(userDTO != null) {
			userDTO.setPassword("");
		}
		return userDTO;
	}

	@Override
	public User getUserByEmailID(String emailID) {
		return userRepository.getUserByEmailID(emailID);
	}

}
