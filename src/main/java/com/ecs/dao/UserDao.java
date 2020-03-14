package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecs.domain.User;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Mapper
public interface UserDao {
	/**
	 * 查找所有的用户
	 * @return
	 */
	@Select("select * from user")
	@Results({
		@Result(property = "id",column = "id"),
		@Result(property = "username",column = "username"),
		@Result(property = "password",column = "password"),
		@Result(property = "identify",column = "identify")
	})
	public List<User> findAll();
	
	/*
	 * 查找单个用户
	 * */
	@Select("select * from user where username = #{username}")
	public User fineOne(String username);
	
	/**
	 * 添加用户
	 * @param user
	 */
	@Insert("Insert into user(username,password,identify) values(#{username},#{password},#{identify})")
	@Results({
		@Result(property = "id",column = "id"),
		@Result(property = "username",column = "username"),
		@Result(property = "password",column = "password"),
		@Result(property = "identify",column = "identify")
	})
	public void addUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	@Delete("delete from user where id=#{id}")
	public void deleteUser(Integer id);
	
	
	/**
	 * 修改用户
	 * @param id
	 */
	@Update("update user set username=#{username},password=#{password},identify=#{identify} where id=#{id}")
	public void updateUser(User user);
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 */
	@Update("update user set password=#{password} where id=#{id}")
	public void changePassword(Integer id,String password);
}
