package ro.agenda;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class ObjectWithList {
 
    private List<Abonat> list;
 
    @XmlElementWrapper(name="Lista AgendaApp")
    @XmlElement(name = "entry")
    public List<Abonat> getList() {
        return list;
    }
 
    public void setList(List<Abonat> list) {
        this.list = list;
    }
    
	public static String marshallingUnmarshalling(JAXBContext jc, ObjectWithList o) throws JAXBException {
		
		Marshaller jaxbMarshaller = jc.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		StringWriter result = new StringWriter();
		jaxbMarshaller.marshal(o, result);

		// Printing XML
		String xml = result.toString();
		System.out.println(xml);
		
		// Creating an Unmarshaller
		Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
		StringReader sr = new StringReader(xml);
		
		//O retr = (O) jaxbUnmarshaller.unmarshal(sr);
		return xml;
		//return retr;
		
	}
 
}

