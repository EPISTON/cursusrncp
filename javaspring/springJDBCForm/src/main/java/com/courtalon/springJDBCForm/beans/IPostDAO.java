package com.courtalon.springJDBCForm.beans;

import java.util.List;

import com.courtalon.springJDBCForm.metier.Post;

public interface IPostDAO {
	List<Post> findAll();
	Post findByID(int id);
	void save(Post p);
}
