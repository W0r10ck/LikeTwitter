package com.myproject.liketwitter.repos;

import com.myproject.liketwitter.domain.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepos extends CrudRepository<Message, Long> {

	List<Message> findByTag (String tag);

}
