package com.ryanfinken.PersonalBlog.data;

import com.ryanfinken.PersonalBlog.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    public List<Post> findAllByOrderByTimestampDesc();
}
