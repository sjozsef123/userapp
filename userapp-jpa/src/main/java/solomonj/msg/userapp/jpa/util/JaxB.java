package solomonj.msg.userapp.jpa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;


import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Author;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;

public class JaxB {

	public static void marshal(Object object) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	public static <T extends Object> void marshal(List<T> objectList, Class<T> cls, String wrapperListTagname, OutputStream output) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Wrapper.class, Book.class, Magazine.class, Newspaper.class, 
					Role.class, User.class, Article.class, Author.class);

			Wrapper<T> wrapper = new Wrapper<>();
			wrapper.setList(objectList);
			
			JAXBElement<Wrapper> jaxbElement = new JAXBElement<Wrapper>(new QName(wrapperListTagname), Wrapper.class, wrapper);
			
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(jaxbElement, output);
			
		} catch (JAXBException | ArrayIndexOutOfBoundsException e) {
			
			e.printStackTrace();
		}
	}
	
	public static <T> List<T> unmarshal(Class<T> cls, InputStream input ) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Wrapper.class, Book.class, Magazine.class, Newspaper.class, 
					Role.class, User.class, Article.class, Author.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			StreamSource xml = new StreamSource(input);
			Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml, Wrapper.class).getValue();
			
			return wrapper.getList();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<>();
	}
}
