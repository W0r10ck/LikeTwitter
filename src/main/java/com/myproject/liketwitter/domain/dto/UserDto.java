package com.myproject.liketwitter.domain.dto;

import com.myproject.liketwitter.domain.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class UserDto {

	private Long id;

	private String username;

	private String password;

	private boolean active;

	private Set<Role> roles;

}
