package com.myproject.liketwitter.repos;

import com.myproject.liketwitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {

	User findByUsername (String username);

}
