package ro.agenda;

import java.time.LocalDate;

public class Abonat {
	
	private static int nrAbonati;
	private int id; 
	private String nume;
	private String prenume;
	private LocalDate data;
	private Adresa adresa;
	private Email email;
	private Telefon telefon;

	public Abonat(){
		nrAbonati++;
		this.id = nrAbonati; 
		this.data = LocalDate.now();
	}
	
	public static int getNrAbonati() {
		return nrAbonati;
	}
	
	public static void setNrAbonati(int abonati) {
		nrAbonati = abonati;
	}
	
	public int getId() {
		return id;
	}

	public Abonat setId(int id) {
		if (id > 0)
			this.id = id;
		return this;
	}
	
	public Abonat setId(String id) {
		if (id != null)
			this.id = Integer.parseInt(id);
		return this;
	}

	public String getNume() {
		return nume;
	}
	
	public Abonat setNume(String nume) {
		if (nume != null && !nume.isEmpty() && nume.length() > 2){
			this.nume = nume;
			return this;
		}
		else
			throw new IllegalArgumentException("Numele trebuie sa aiba minim 3 caractere.");
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public Abonat setPrenume(String prenume) {
		if (prenume != null && !prenume.isEmpty() && prenume.length() > 2){
			this.prenume = prenume;
			return this;
		}
		else
			throw new IllegalArgumentException("Prenumele trebuie sa aiba minim 3 caractere.");
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public Abonat setData(LocalDate data) {
		if (data != null){
			this.data = data;
		}
		return this;
	}
	
	public Abonat setData(String data) {
		if (data != null){
			this.data = LocalDate.parse(data);
		}
		return this;
	}
	
	public Adresa getAdresa() {
		return adresa;
	}

	public Abonat setAdresa(Adresa adresa) {
		if (adresa != null)
			this.adresa = adresa;
		return this;
	}
	
	public Email getEmail() {
		return email;
	}

	public Abonat setEmail(Email email) {
		if (email != null)
			this.email = email;
		return this;
	}

	public Telefon getTelefon() {
		return telefon;
	}

	public Abonat setTelefon(Telefon telefon) {
		if (telefon != null)
			this.telefon = telefon;
		return this;
	}
	
	@Override
	public String toString() {
		return "Abonatul " + id + " cu numele " + nume + " " + prenume 
				+ " introdus la data " + data + ". Adresa: " 
				+ adresa +  "; Contact: " + telefon + " - email: " + email + ".";
	}
	
	public String toFileString() {
		//id	nume	prenume		data	adresa		nrTel		email	
		return id + "," + Controller.toNonEmpty(nume) + "," + Controller.toNonEmpty(prenume) + "," + data //dateFormatter.format(data) 
		+ "," + adresa + "," + telefon + "," + email ;
	}

	
	
}
