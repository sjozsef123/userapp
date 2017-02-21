package solomonj.msg.userapp.ejb.service.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;

@Singleton
@Startup
public class ShowTime {

	@EJB
	private IBorrowingRepository borrowingRepositoryBean;

	@Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
	public void checkBorrowing() {
//		LIst<Borrowing> asd = borrowingRepositoryBean.getValami();
//		id = asd.getUserId();
//		userBean.getUserById(id).getEmail;
//		IEmailApi.sendEmai()
	}

	@PostConstruct
	public void startUp() {
		//showTime();
	}
}
