package solomonj.msg.userapp.ejb.repository.bean;

import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.jpa.model.Magazine;

public class MagazineRepositoryBean extends BasicRepositoryBean<Magazine>implements IMagazineRepository{

	public MagazineRepositoryBean() {
		super(Magazine.class);
	}

}
