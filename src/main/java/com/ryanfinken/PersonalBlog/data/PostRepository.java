package com.ryanfinken.PersonalBlog.data;

import com.ryanfinken.PersonalBlog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
