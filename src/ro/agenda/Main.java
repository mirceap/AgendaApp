package ro.agenda;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*		Adresa adresa = new Adresa();
		adresa.setStrada("Mircea Voda").setNumarStrada("24").setBloc("2")
			  .setScara("2").setNrApartament("100").setCodPostal("901491").setOras("Bucuresti");
		Telefon telefon = new Telefon("+40755170510");
		Abonat a = new Abonat().setNume("Pavel").setPrenume("Mircea")
				.setData(LocalDate.now()).setAdresa(adresa).setTelefon(telefon).setEmail(new Email("pavelmircea92@gmail.com"));
		 */
		Scanner reader = new Scanner(System.in);
		List<Abonat> listaContacte = metodaRestore(reader);
		System.out.println("Bun venit in Aplicatia Agenda!\n\tOptiuni:\n" +
					"1 - Adaugare contact in format XML\n" +
					"2 - Listare contactele stocate\n" +
					"3 - Cautare contact\n" +
					"4 - Actualizeaza contact\n" +
					"5 - Stergere contact\n" +
					"6 - Salvare in format XML\n" +
					"7 - Salvare contacte in fisier text(Save As...)\n" +
					"0 - Iesire aplicatie\n");
		
		  // Reading from System.in
		System.out.println("Alege optiune: ");
		Integer optiune = Integer.parseInt(reader.nextLine());
		
		while(optiune != 0){
			if (optiune == 1){
				//Controller.salveazaXML(listaContacte);
				//jaxbObjectToXML();
				//parseObjToXML(listaContacte);
				//System.out.println(saveToXML(listaContacte));
				System.out.println("Nu exista implementare inca.");
			}
			else if (optiune == 2){
				listareContacte(listaContacte);
			}
			else if (optiune == 3){
				Controller.cautaContactInLista(listaContacte, reader);
			}
			else if (optiune == 4){
				Controller.updateContact(listaContacte, reader);
			}
			else if (optiune == 5){
				Controller.stergeContactDinLista(listaContacte, reader);
			}
			else if (optiune == 6){
				try {
					saveToXmlFile(listaContacte);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (optiune == 7){
				metodaSalvare(listaContacte, reader);
			}
			System.out.println("Alege optiune (0 pentru iesire): ");
			optiune = Integer.parseInt(reader.nextLine());
		}
		
		// salvare in fisier inainte de iersire;
		System.out.println("Salvati inainte de iesire? \n\t0 - da\t1 - nu");
		optiune = Integer.parseInt(reader.nextLine());
		if (optiune.intValue() == 0){
			metodaSalvare(listaContacte, reader);
		}
		
		reader.close();
		return;
	}
	
	public static void metodaSalvare(List<Abonat> listaContacte, Scanner reader){
		System.out.println("Introduceti numele fisierului(fara extensia .txt): ");
		String nume = reader.nextLine();
		Controller.salveazaContacteInFisier(listaContacte, nume.concat(".txt"));
	}
	
	public static List<Abonat> metodaRestore(Scanner reader){
		System.out.println("Introduceti numele fisierului unde ati salvat contactele(fara extensia .txt): ");
		String numeFisier = reader.nextLine();
		List<Abonat> listaContacte = Controller.citesteContacte(numeFisier.concat(".txt"));
		
		return listaContacte;
	}
	
	public static void listareContacte(List<Abonat> listaContacte){
		listaContacte.forEach(abonat -> System.out.println(abonat.toString()));
		return;
	}
	
	public static void parseObjToXML(List<Abonat> lista){
			/*Adresa adresa = new Adresa();
			adresa.setStrada("Mircea Voda").setNumarStrada("24").setBloc("2")
			.setScara("2").setNrApartament("100").setCodPostal("901491").setOras("Bucuresti");
			Telefon telefon = new Telefon("+40755170510");
			Abonat a = new Abonat().setNume("Pavel").setPrenume("Mircea")
					.setData(LocalDate.now()).setAdresa(adresa).setTelefon(telefon).setEmail(new Email("pavelmircea92@gmail.com"));*/
			
			StringWriter sw = new StringWriter();
			lista.forEach(contact -> {
				JAXB.marshal(contact, sw);
				String xmlString = sw.toString();
				try {
					stringToDom(xmlString, "listaContacte.xml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(xmlString);
			});
			System.out.println("XML saved!");
			
	return;
	}
	
	public static void stringToDom(String xmlString, String fileName) 
	        throws IOException {
	    java.io.FileWriter fw = new java.io.FileWriter(fileName);
	    fw.write(xmlString);
	    fw.close();
	}
	
	public static void parseXMLtoObj(String xmlString){
		Abonat ab = JAXB.unmarshal(new StringReader(xmlString), Abonat.class);
	}
	
	
	private static String jaxbObjectToXML() {
		
		Adresa adresa = new Adresa();
		adresa.setStrada("Mircea Voda").setNumarStrada("24").setBloc("2")
		.setScara("2").setNrApartament("100").setCodPostal("901491").setOras("Bucuresti");
		Telefon telefon = new Telefon("+40755170510");
		Abonat a = new Abonat().setNume("Pavel").setPrenume("Mircea")
				.setData(LocalDate.now()).setAdresa(adresa).setTelefon(telefon).setEmail(new Email("pavelmircea92@gmail.com"));

		
	    String xmlString = "";
	    try {
	        JAXBContext context = JAXBContext.newInstance(Abonat.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	        StringWriter sw = new StringWriter();
	        m.marshal(a, sw);
	        xmlString = sw.toString();

	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }

	    return xmlString;
	}
	
	private static JaxbList<Abonat> saveToXML(List<Abonat> lista){
		StringWriter sw = new StringWriter();
		JAXB.marshal(lista, sw);
		String xmlString = sw.toString();
		try {
			stringToDom(xmlString, "listaContacte2.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(xmlString);
		System.out.println("XML saved!");
		return new JaxbList<Abonat> (lista);
	}
	
	public static void saveToXmlFile(List<Abonat> lista) throws JAXBException {
		 	    
	    // Object with list
	    ObjectWithList owl = new ObjectWithList();
	    owl.setList(lista);
	 
	    JAXBContext jc = JAXBContext.newInstance(ObjectWithList.class);
	    try {
			stringToDom(ObjectWithList.marshallingUnmarshalling(jc, owl), "listaXML.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
/*	    for (Abonat s : retr.getList()) {
	        System.out.println(s);
	    } System.out.println(" ");*/
	}
}
