package com.jt.dubbo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController
public class UserController {
	//timeout=3000，超时连接3秒
	//check=false不检查可用的服务；默认是true，如果没有可用的服务就会报错
	@Reference(timeout=3000,check=false)
	/**
	 * 负载均衡策略：
	 * loadbalance="random" 随机（默认）
	 * loadbalance="roundRobin" 轮询（基于权重是一样的基础上）
	 * loadbalance="leastActive" 挑选负载最少服务器
	 * loadbalance="consistentHash" 绑定服务器ip地址
	 * 
	 */
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		
		return userService.findAll();
	}
	
	@RequestMapping("/saveUser/{name}/{age}/{sex}")//restFul动态接收参数
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
	
	@RequestMapping("/deleteUserById")
	public String deleteUserById(Integer...id) {
		
		userService.deleteUserById(id);
		return "用户删除成功!!!";
	}
	//test
}
