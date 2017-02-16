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
import solomonj.msg.userapp.ejb.util.InfoMessages;
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
			oLogger.info(InfoMessages.LIST_USERS);
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
		} catch (ServiceException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException("user.create");
		}

		try {
			oLogger.info(InfoMessages.CREATE_USER);
			userRepositoryBean.create(user);
			oLogger.info(InfoMessages.CREATE_USER_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteUserById(User user) throws ServiceException {
		try {
			oLogger.info(InfoMessages.DELETE_USER);
			userRepositoryBean.delete(user);
			oLogger.info(InfoMessages.DELETE_USER_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			oLogger.info(InfoMessages.UPDATE_USER);
			userRepositoryBean.update(user);
			oLogger.info(InfoMessages.UPDATE_USER_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws ServiceException {
		try {
			oLogger.info(InfoMessages.SEARCH_USERS_BY_NAME);
			return userRepositoryBean.searchUserByName(name);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public User login(String name, String password) throws ServiceException {
		String pass;
		try {
			pass = PasswordEncrypting.encrypt(password, "user");
		} catch (ServiceException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException("user.login");
		}
				
		try {
			oLogger.info(name + InfoMessages.LOGIN_USER);
			return userRepositoryBean.login(name, pass);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
