package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.BaseEntity;

public interface IBasicRepository<T extends BaseEntity> {

	List<T> getlAll() throws RepositoryException;
	void create(T publication) throws RepositoryException;
	void delete(T publication) throws RepositoryException;	
	void update(T publication) throws RepositoryException;
}
