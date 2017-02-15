package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IUserService;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.service.util.PasswordEncrypting;
import solomonj.msg.userapp.jpa.model.User;

/**
 * This session bean manages the users.
 * 
 * @author Solomon Jozsef
 * @author Simo Zoltan
 *
 */
@Stateless
public class UserServiceBean implements IUserService {

	@EJB
	private IUserRepository userRepositoryBean;
	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			oLogger.info("");
			return userRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		try {
			user.setPassword(PasswordEncrypting.encrypt(user.getPassword(), "user"));
			oLogger.info("");
		} catch (ServiceException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException("user.create");
		}

		try {
			userRepositoryBean.create(user);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteUserById(User user) throws ServiceException {
		try {
			userRepositoryBean.delete(user);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			userRepositoryBean.update(user);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws ServiceException {
		try {
			oLogger.info("");
			return userRepositoryBean.searchUserByName(name);

		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public User login(String name, String password) throws ServiceException {
		// check name length password, encrypt
		String pass = PasswordEncrypting.encrypt(password, "user");
		try {
			oLogger.info("");
			return userRepositoryBean.login(name, pass);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
