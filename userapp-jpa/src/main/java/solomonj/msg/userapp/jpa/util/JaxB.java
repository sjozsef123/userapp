package solomonj.msg.userapp.jpa.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JaxB {

	public static void marshall(Object object) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}
