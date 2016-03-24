package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BaseDAO;
import dto.TestDTO;
import dto.UserDTO;
import utils.EncryptionUtils;

@Service
public class LoginService {
	
	@Autowired
	protected BaseDAO baseDAO;
	
	public TestDTO getUserById(String id){
		return this.baseDAO.selectOne(id);
	}

	public void saveUser(UserDTO user) {
		user.setPassword(EncryptionUtils.md5(user.getPassword()));
		this.baseDAO.insert(user);
	}

	public String login(UserDTO user) {
		// TODO Auto-generated method stub
		return "main";
	}

}
