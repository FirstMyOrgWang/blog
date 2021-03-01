package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.UserDao;
import blog.entity.User;

@Service
public class UserService {
    
	  @Autowired
	  private UserDao userDao;
	  
	  public User get(String id) {
		  
		  return userDao.get(id);
		  
	  }
	
	
}
