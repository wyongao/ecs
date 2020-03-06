package com.hgc.ecs;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecs.domain.User;
import com.ecs.service.UserService;

@SpringBootTest
class EcsApplicationTests {
	@Autowired
	private UserService userService;
	@Test
	public void findAll() {
		
	  List<User> list= userService.findAll();
	  for (User user : list) {
		System.out.println("----------->"+user);
	}
	}
}
