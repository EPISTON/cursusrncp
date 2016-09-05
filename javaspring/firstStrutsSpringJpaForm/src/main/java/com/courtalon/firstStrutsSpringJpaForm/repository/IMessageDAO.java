package com.courtalon.firstStrutsSpringJpaForm.repository;

import java.util.List;

import com.courtalon.firstStrutsSpringJpaForm.metier.Message;

public interface IMessageDAO {
	List<Message> findAll();
	Message findByID(int id);
	Message save(Message m);
	void remove(int id);

}
