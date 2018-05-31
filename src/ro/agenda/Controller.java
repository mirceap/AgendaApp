package ro.agenda;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public abstract class Controller implements IOperatiuni{
	
	public boolean creeazaContact(String fileName){
		
		// TO-DO: aici implementare pentru citire XML din line de comanda
		BufferedReader bf = null;
		String input = null;
		
		System.out.println("Introduceti nume: ");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			
			input = bf.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		} finally{
			System.out.println(input);
		}
		return true;
	}
	
	// citire din fisier text
	public static Abonat citestePrimulContact(String fileName){
		Abonat ab = new Abonat();
		
	    BufferedReader br = null;
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(fileName));

            String[] abonat = br.readLine().split(cvsSplitBy);

            Adresa adresa = new Adresa();
            adresa.fromString(abonat[4].split(";"));
            ab.setNume(abonat[1]).setPrenume(abonat[2]).setData(abonat[3]).setAdresa(adresa)
            .setTelefon(new Telefon((abonat[5]))).setEmail(new Email(abonat[6]));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return ab;
	}

	public static List<Abonat> citesteContacte(String fileName){
		List<Abonat> listaAbonati = new ArrayList<Abonat>();	
		
	    BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {

                String[] abonat = line.split(cvsSplitBy);
                Abonat.setNrAbonati(Integer.parseInt(abonat[0]) - 1);
                
                Adresa adresa = new Adresa();
                Abonat ab = new Abonat();
                if (!abonat[4].contentEquals("null") && !abonat[4].isEmpty())
                 	adresa.fromString(abonat[4] != null ? abonat[4].split(";") : null);
                
               ab.setId(abonat[0]).setNume(abonat[1]).setPrenume(abonat[2]).setData(abonat[3]).setAdresa(adresa)
               	.setTelefon(new Telefon((abonat[5]))).setEmail(new Email(abonat[6]));
               listaAbonati.add(ab);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Abonat.setNrAbonati(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return listaAbonati;
	}
	
	// salvare 
	public static void salveazaContacteInFisier(List<Abonat> lista, String fileName){
		try(PrintWriter out = new PrintWriter(fileName)){
			lista.forEach(contact -> out.println(contact.toFileString()));
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void salveazaXML(List<Abonat> lista){
		final String SERIALIZED_FILE_NAME="listaContacte.xml";
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File listaContacte.xml");
		}
		encoder.writeObject(lista);
		for (Iterator<Abonat> iter = lista.iterator(); iter.hasNext(); ) {
		    Abonat element = iter.next();
		    //String xml = xstream.toXML(joe);
		    encoder.writeObject(element);
		}
		encoder.close();
	}
	
	public static boolean adaugaContactLaFinal(Abonat ab, String fileName){
		BufferedWriter bw = null;
		FileWriter fw = null;
		File f = null;
		
		if (fileName != null)
			f = new File(fileName);
		
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(ab.toFileString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	// stergere
	public static Abonat stergeContactDinLista(List<Abonat> listaContacte, Scanner reader){
		System.out.println("Introduceti numarul contactului pe care doriti sa il stergeti: ");
		Abonat searchedContact = cautaDupaId(listaContacte, reader);
		System.out.println("Contactul pe care urmeaza sa il stergeti: \n" + searchedContact.toFileString() 
				+ "\nConfirmare:\n\t0 - da\t1 - nu");
		Integer optiune = Integer.parseInt(reader.nextLine());
		if (optiune.intValue() == 0){
			for (Iterator<Abonat> iter = listaContacte.iterator(); iter.hasNext(); ) {
			    Abonat contact = iter.next();
			    if (contact == searchedContact){
					iter.remove();
				}
			}	
		}
		else{
			System.out.println("Contactul nu a fost sters!");
		}

		return searchedContact;
	}
	
	public static Abonat stergeContactDinFisier(String keyword, String fileName){
		List<Abonat> listaContacte = citesteContacte(fileName);
		Abonat ab = null;
		for (final Abonat contact : listaContacte){
			if (contact.toFileString().contains(keyword)){
				ab = contact;
				listaContacte.remove(contact);
			}
		}
		salveazaContacteInFisier(listaContacte, fileName);
		return ab;
	}
	
	// cautare
	public static List<Abonat> cautaContactInLista(List<Abonat> listaContacte, Scanner reader){
		List<Abonat> ab = new ArrayList<Abonat>();
		
		System.out.println("Introduceti cuvantul dupa care vreti sa cautati contactul (nume, prenume, adresa etc.): ");
		String keyword = reader.nextLine();
/*		
 * 		TO-DO: cauta o anumita proprietate
 * 
 * 		Abonat myStuff = new Abonat();
		//Field[] fields = searchedContact.getClass().getDeclaredFields();
		//Field searchedField = null;
		
		/*		try {
//				searchedField = searchedContact.getClass().getDeclaredField(field);
				//searchedContact.getClass().getMethod(name, parameterTypes)
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println("Nu exista un camp cu denumirea introdusa");
				//e.printStackTrace();
			}*/
			
/*			System.out.println(searchedField);
			int len = fields.length;
			for (int i = 0; i < len; i++){
				if (fields[i].toString().contains(field)){
					System.out.println(fields[i].toString());
					System.out.println(searchedContact);
				}
			}
		
		if (keyword.matches("nume")){
			
		}
			*/
			
		for (final Abonat contact : listaContacte){
			if (contact.toFileString().contains(keyword)){
				ab.add(contact);
				System.out.println(contact.toString());
			}
		}
		return ab;
	}
	
	private static Abonat cautaDupaId(List<Abonat> listaContacte, Scanner reader){
		Abonat searchedContact = null;
		Integer searchedId = Integer.parseInt(reader.nextLine());
				
		for (Abonat contact : listaContacte){
			if (contact.getId() == searchedId){
				searchedContact = contact;
			}
		}
		return searchedContact;
	}
	
	// actualizare
	public  static boolean updateContact(List<Abonat> listaContacte, Scanner reader){
		System.out.println("Introduceti numarul contactului pe care doriti sa il modificati: ");
		Abonat searchedContact = cautaDupaId(listaContacte, reader);
		
		if (searchedContact != null){
			
			System.out.println("Selectati atributul pe care doriti sa il modificati: "
					+ "\n1 - nume"
					+ "\n2 - prenume"
					+ "\n3 - adresa"
					+ "\n4 - numar telefon"
					+ "\n5 - email" );
			Integer field = Integer.parseInt(reader.nextLine());
			
			if (field.intValue() == 1){
				System.out.println("Introduceti noul nume: ");
				searchedContact.setNume(reader.nextLine());
				searchedContact.setData(LocalDate.now());
				System.out.println("Contact modificat!");
			}
			else if (field.intValue() == 2){
				System.out.println("Introduceti noul prenume: ");
				searchedContact.setPrenume(reader.nextLine());
				searchedContact.setData(LocalDate.now());
				System.out.println("Contact modificat!");
			}
			else if (field.intValue() == 3){
				System.out.println("Introduceti noua adresa: ");
				Adresa adresa = Adresa.fromUserInput(reader);
				searchedContact.setAdresa(adresa);
				searchedContact.setData(LocalDate.now());
				System.out.println("Contact modificat!");
			}
			else if (field.intValue() == 4){
				System.out.println("Introduceti noul numar de contact: ");
				searchedContact.setTelefon(new Telefon(reader.nextLine()));
				searchedContact.setData(LocalDate.now());
				System.out.println("Contact modificat!");
			}
			else if (field.intValue() == 5){
				System.out.println("Introduceti noul email: ");
				searchedContact.setEmail(new Email(reader.nextLine()));
				searchedContact.setData(LocalDate.now());
				System.out.println("Contact modificat!");
			}
			else{
				System.out.println("Optiunea aleasa nu exista!\nReincercati!");
				return false;
			}
		}
		else{
			System.out.println("Numarul introdus nu corespunde niciunui contact din agenda. Reincercati!");
			return false;
		}

		return true;
	}
	
	public static String toNonEmpty(String s){
		if (s != null && !
				s.contentEquals("null"))
			return s;
		return "-";
	}
}
