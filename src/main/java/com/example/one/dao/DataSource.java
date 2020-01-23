package com.example.one.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.example.one.model.UserReg;

public class DataSource {
	static Random rand = new Random();
	static Md5PasswordEncoder encoder = new Md5PasswordEncoder();

	public static List<UserReg> users = new ArrayList<UserReg>() {
		{
			new UserReg("a", "test", "ab", "a@gmail.com",
					encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash"));
			new UserReg("b", "test", "bb", "b@gmail.com",
					encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash"));
			new UserReg("c", "test", "cb", "c@gmail.com",
					encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash"));
			new UserReg("d", "test", "db", "d@gmail.com",
					encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash"));
			new UserReg("e", "test", "eb", "e@gmail.com",
					encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash"));
		}
	};

	{
		addAllUser();
	}

	public List<UserReg> getAllUser() {
		// TODO Auto-generated method stub
		if (users == null || users.size() == 0)
			addAllUser();
		return users;
	}

	public void addAllUser() {
		users = new ArrayList<>();
		Random rand = new Random();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash");

		users.add(new UserReg("a", "test", "ab", "a@gmail.com",
				encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash")));
		users.add(new UserReg("b", "test", "bb", "b@gmail.com",
				encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash")));
		users.add(new UserReg("c", "test", "cb", "c@gmail.com",
				encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash")));
		users.add(new UserReg("d", "test", "db", "d@gmail.com",
				encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash")));
		users.add(new UserReg("e", "test", "eb", "e@gmail.com",
				encoder.encodePassword(String.valueOf(rand.nextInt(1000000)), "hash")));
	}

	public List<UserReg> getAllUserLike(String username) {
		// TODO Auto-generated method stub
		if (users == null || users.size() == 0)
			addAllUser();
		return users.stream().filter(p -> p.getUsername().contains(username)).collect(Collectors.toList());
	}

}
