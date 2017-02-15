package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Magazine;

public interface IMagazineRepository extends IPublicationRepository<Magazine>{

	//ide az egyeni metodusok
	List<Magazine> filterMagazineByName(String filter) throws RepositoryException;
}
