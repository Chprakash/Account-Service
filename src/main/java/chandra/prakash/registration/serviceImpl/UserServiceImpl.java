package chandra.prakash.registration.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import chandra.prakash.registration.dto.UserDTO;
import chandra.prakash.registration.dto.UserLoginDTO;
import chandra.prakash.registration.entity.TUser;
import chandra.prakash.registration.repository.RoleRepository;
import chandra.prakash.registration.repository.UserRepository;
import chandra.prakash.registration.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private TUser DTOToEntity(UserDTO DTO) {
		TUser entity = new TUser();
		entity.setId(DTO.getId());
		entity.setFirstName(DTO.getFirstName());
		entity.setLastName(DTO.getLastName());
		entity.setEmailID(DTO.getEmailID());
		entity.setPassword(DTO.getPassword());
		entity.setStatus(DTO.getStatus());
		return entity;
	}
	
	private UserDTO EntityToDTO(TUser entity) {
		UserDTO DTO = new UserDTO();
		DTO.setId(entity.getId());
		DTO.setFirstName(entity.getFirstName());
		DTO.setLastName(entity.getLastName());
		DTO.setEmailID(entity.getEmailID());
		DTO.setStatus(entity.getStatus());
		return DTO;
	}

	@Override
	public UserDTO getUserByID(Long id) {
		TUser user = userRepository.getOne(id);
		System.out.println(bCryptPasswordEncoder.encode(user.getPassword()));
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmailID(user.getEmailID());
		return userDTO;
	}

	@Override
	public UserDTO addNewUser(UserDTO userDTO) {
		TUser user = DTOToEntity(userDTO);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setRole(roleRepository.getOne(userDTO.getRole()));
		userDTO = EntityToDTO(userRepository.save(user));
		if(userDTO != null) {
			userDTO.setPassword("");
		}
		return userDTO;
	}
	

	@Override
	public UserDTO getUserLogin(UserLoginDTO userLoginDTO) {
		Boolean validUser = false;
		UserDTO userDTO = null;
		TUser user = userRepository.getUserByEmailID(userLoginDTO.getEmailID());
		if(user != null) {
			validUser = bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
			if(validUser) {
				userDTO = EntityToDTO(user);
			}
		}
		
		return userDTO;
	}

	@Override
	public TUser getUserByEmailID(String emailID) {
		return userRepository.getUserByEmailID(emailID);
	}

}
