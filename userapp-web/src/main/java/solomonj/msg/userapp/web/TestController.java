package solomonj.msg.userapp.web;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("testcontroller")
@ApplicationScoped
public class TestController implements Serializable {
	private static final long serialVersionUID = 1L;

    private String text;

    public String getTeste()
    {
        return text;
    }

    public void setTeste(String teste)
    {
        this.text = teste;
    }
}
