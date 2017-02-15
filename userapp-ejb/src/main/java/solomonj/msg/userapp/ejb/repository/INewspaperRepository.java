package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Newspaper;

public interface INewspaperRepository extends IPublicationRepository<Newspaper>{

	//ide az egyeni metodusok
	List<Newspaper> filterNewspaperByName(String filter) throws RepositoryException;
}
