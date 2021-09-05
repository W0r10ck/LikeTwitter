package com.myproject.liketwitter.domain.utils;

import com.myproject.liketwitter.domain.User;
import com.myproject.liketwitter.domain.dto.UserDto;

public class UserUtils {


	public static User getUserFromDto (UserDto userDto){

		return User.init().setUsername(userDto.getUsername())
				.setPassword(userDto.getPassword())
				.setActive(userDto.isActive())
				.setRoles(userDto.getRoles())
				.build();

	}


}
