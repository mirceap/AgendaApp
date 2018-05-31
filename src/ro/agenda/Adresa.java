package ro.agenda;

import java.util.Scanner;

public class Adresa {
	private String strada;
	private String numarStrada;
	private String bloc;
	private String scara;
	private String nrApartament;
	private String oras;
	private String codPostal;
	
	public String getStrada() {
		return strada;
	}
	public Adresa setStrada(String strada) {
		if (strada != null)
			this.strada = strada;
		return this;
	}
	public String getNumarStrada() {
		return numarStrada;
	}
	public Adresa setNumarStrada(String numar) {
		if (numar != null)
			this.numarStrada = numar;
		return this;
	}
	public String getBloc() {
		return bloc;
	}
	public Adresa setBloc(String bloc) {
		if (bloc != null)
			this.bloc = bloc;
		return this;
	}
	public String getScara() {
		return scara;
	}
	public Adresa setScara(String scara) {
		if (scara != null)
			this.scara = scara;
		return this;
	}
	public String getNrApartament() {
		return nrApartament;
	}
	public Adresa setNrApartament(String nrApartament) {
		if (nrApartament != null)
			this.nrApartament = nrApartament;
		return this;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public Adresa setCodPostal(String codPostal) {
		if (codPostal != null)
			this.codPostal = codPostal;
		return this;
	}
	public String getOras() {
		return oras;
	}
	public Adresa setOras(String oras) {
		if (oras != null)
			this.oras = oras;
		return this;
	}

	
	@Override
	public String toString() {
		return "Strada: " + Controller.toNonEmpty(strada) + "; Nr.: " + Controller.toNonEmpty(numarStrada) 
		+ "; Bloc: " + Controller.toNonEmpty(bloc) + "; Sc.: " + Controller.toNonEmpty(scara)
		+ "; Ap.: " + Controller.toNonEmpty(nrApartament)+ "; C.P.: " + Controller.toNonEmpty(codPostal) 
		+ "; Oras: " + Controller.toNonEmpty(oras);
	}
	
	public String fromFormat(String field){
		return field.split(":")[1].trim();
	}
	
	public Adresa fromString(String[] adresaString){
		if (adresaString[0] != "null" && adresaString[0] != null && adresaString[1] != null && adresaString[2] != null &&
			adresaString[3] != null && adresaString[4] != null && adresaString[5] != null &&
			adresaString[6] != null){
			this.setStrada(fromFormat(adresaString[0])).setNumarStrada(fromFormat(adresaString[1]))
		    	.setBloc(fromFormat(adresaString[2])).setScara(fromFormat(adresaString[3]))
		    	.setNrApartament(fromFormat(adresaString[4])).setCodPostal(fromFormat(adresaString[5]))
		    	.setOras(fromFormat(adresaString[6]));
		}
		return this;
	}
	
	public static Adresa fromUserInput(Scanner reader){
		Adresa ad = new Adresa();
		System.out.println("Introduceti strada: ");
		ad.setStrada(reader.nextLine());
		System.out.println("Introduceti numar strada: ");
		ad.setNumarStrada(reader.nextLine());
		System.out.println("Introduceti bloc: ");
		ad.setBloc(reader.nextLine());
		System.out.println("Introduceti scara: ");
		ad.setScara(reader.nextLine());
		System.out.println("Introduceti numar apartament: ");
		ad.setNrApartament(reader.nextLine());
		System.out.println("Introduceti oras: ");
		ad.setOras(reader.nextLine());
		System.out.println("Introduceti cod postal: ");
		ad.setCodPostal(reader.nextLine());
		return ad;
	}
	
}
