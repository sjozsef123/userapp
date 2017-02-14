package solomonj.msg.userapp.ejb.repository;

import javax.ejb.Local;

import solomonj.msg.userapp.jpa.model.Role;

/**
 * Interface for handling roles.
 * 
 * @author Solomon Jozsef
 */
@Local
public interface IRoleRepository extends IBasicRepository<Role> {

}
