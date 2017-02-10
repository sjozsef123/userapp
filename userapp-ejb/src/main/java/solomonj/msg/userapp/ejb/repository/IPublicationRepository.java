package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;

public interface IPublicationRepository<T extends Publication> extends IBasicRepository<T>{

	List<T> filterPublicationByName(String filter) throws RepositoryException;
}
