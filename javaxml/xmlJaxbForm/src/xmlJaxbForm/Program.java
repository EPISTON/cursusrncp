package xmlJaxbForm;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Program {

	public static void main(String[] args) {
		Menestrel m1 = new Menestrel(1, "caradoc",
									"caradoc@lafleche.com",
									"pipeau", 45);
		
		File f = new File("menestrel.xml");
		try {
			JAXBContext  jaxbContext = JAXBContext.newInstance(Menestrel.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// je veux l'indentitation en sortie
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(m1, f);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Menestrel m2 = (Menestrel) jaxbUnmarshaller.unmarshal(
										new File("menestrelbis.xml"));
			System.out.println(m2);
			System.out.println("terminé");
		} catch (JAXBException e) {e.printStackTrace();}
		
	}

}
