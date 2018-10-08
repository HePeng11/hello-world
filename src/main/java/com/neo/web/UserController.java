package com.neo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.neo.entity.UserEntity;
import com.neo.mapper.UserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户相关接口")
@RestController
public class UserController {
	private static Integer i = 1;
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() { 
		List<UserEntity> users = userMapper.getAll();
		System.out.println(i++);
		return users;
	}

	@ApiOperation(value = "用户详细信息", notes = "通过id获得用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", defaultValue = "0") })
	@RequestMapping("/getUser")
	public UserEntity getUser(Long id) {
		UserEntity user = userMapper.getOne(id);
		return user;
	}

	@ApiOperation(value = "添加用户", notes = "添加用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", value = "userentity", required = true, paramType = "?", defaultValue = "0") })
	@PostMapping("/add")
	public void save(UserEntity user) {
		userMapper.insert(user);
	}

	@RequestMapping(value = "update")
	public void update(UserEntity user) {
		userMapper.update(user);
	}

	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		userMapper.delete(id);
	}
	
	@RequestMapping(value = "/GetUserPage")
	public List<UserEntity> GetUserPage(Integer page,Integer rows){
		
		PageHelper.startPage(page,rows);
        return userMapper.getAll();
	}

}