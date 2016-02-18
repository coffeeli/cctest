package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BaseDAO;
import dto.TestDTO;

@Service
public class TestService {
	
	@Autowired
	protected BaseDAO baseDAO;
	
	public TestDTO getUserById(String id){
		return this.baseDAO.selectOne(id);
	}

	public void saveUser(TestDTO user) {
		this.baseDAO.insert(user);
	}

}
