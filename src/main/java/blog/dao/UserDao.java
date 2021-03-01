package blog.dao;

import org.apache.ibatis.annotations.Mapper;

import blog.entity.User;

@Mapper
public interface UserDao {

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);
	
	
    /**获取用户信息
     * @param id
     * @return
     */
    public User get(String id);
    

    /**获取用户信息
     * @param id
     * @return
     */
    public User get(User user);
 
}