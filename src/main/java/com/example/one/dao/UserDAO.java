package com.example.one.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.example.one.config.Config;
import com.example.one.model.UserReg;

public class UserDAO {
	private DataSource dataSource;

	public List<UserReg> getAll() {
		return dataSource.getAllUser();

	}

	public Map<String, Object> getCurrentUserInfo(String username) {
		UserReg ur = DataSource.users.stream().filter(u -> username.equals(u.getUsername())).findAny().orElse(null);
		return Collections.singletonMap(username, ur);

	}

	public String getDisplayName(String username) {
		Map<String, Object> mp = getCurrentUserInfo(username);
		return ((UserReg) mp.get(username)).getUsername();

	}

	public int insert(String username, String password, String email, String displayName) {
		// String sql = "INSERT INTO user VALUES (?, ?, ?, ?, 0, NOW(), NOW(), ?, ?,
		// 0)";

		DataSource.users.add(new UserReg(username, password, displayName, email,
				new Md5PasswordEncoder().encodePassword(String.valueOf(new Random().nextInt(1000000)), "hash")));
		return 1;
	}

	public String getOldPassword(String username) {
		Map<String, Object> mp = getCurrentUserInfo(username);
		return ((UserReg) mp.get(username)).getPassword();
	}

	public String verify(String username, String hash) {
		Map<String, Object> mp = getCurrentUserInfo(username);
		UserReg ur = ((UserReg) mp.get(username));
		if (ur.getHash().equals(hash)) {
			return "Verify successfully";
		}

		return "Verify unsuccessfully";
	}

	public String getVerifyLink(String username) {
		Map<String, Object> mp = getCurrentUserInfo(username);
		UserReg ur = ((UserReg) mp.get(username));

		return Config.getConfig("hostname") + "/api/verify/" + ur.getUsername() + "/" + ur.getHash();
	}

	public String getEmail(String username) {
		Map<String, Object> mp = getCurrentUserInfo(username);
		return ((UserReg) mp.get(username)).getEmail();

	}

	public List<UserReg> getUserByUsername(String username) {
		return dataSource.getAllUserLike(username);

	}

	public List<UserReg> getUserList() {

		return getAll();
	}

}
