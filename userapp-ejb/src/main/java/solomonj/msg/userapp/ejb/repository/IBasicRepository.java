package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.jpa.model.BaseEntity;

public interface IBasicRepository<T extends BaseEntity> {

	List<T> getlAll();
	void create(T publication);
	void delete(T publication);	
	void update(T publication);
}
