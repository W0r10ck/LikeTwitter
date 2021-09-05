package com.myproject.liketwitter.service;

import com.myproject.liketwitter.repos.UserRepos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private final UserRepos userRepos;

	public UserService(final UserRepos userRepos) {
		this.userRepos = userRepos;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return userRepos.findByUsername(username);
	}
}
