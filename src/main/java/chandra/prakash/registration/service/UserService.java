package chandra.prakash.registration.service;

import chandra.prakash.registration.dto.UserDTO;
import chandra.prakash.registration.dto.UserLoginDTO;
import chandra.prakash.registration.entity.TUser;

public interface UserService {
	public UserDTO getUserByID(Long id);
	public UserDTO addNewUser(UserDTO clientDTO);
	public UserDTO getUserLogin(UserLoginDTO clientDTO);
	public TUser getUserByEmailID(String emailID);
}
