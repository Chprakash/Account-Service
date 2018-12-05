package chandra.prakash.registration.service;

import chandra.prakash.registration.dto.UserDTO;
import chandra.prakash.registration.entity.User;

public interface UserService {
	public UserDTO getUserByID(Long id);
	public UserDTO addNewUser(UserDTO clientDTO);
	public UserDTO getUserLogin(UserDTO clientDTO);
	public User getUserByEmailID(String emailID);
}
