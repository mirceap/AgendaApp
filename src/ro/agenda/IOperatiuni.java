package ro.agenda;

public interface IOperatiuni {
	boolean adaugaContact(Abonat ab, String fileName);
	boolean stergeContact(Abonat ab, String fileName);
	boolean updateContact(Abonat ab, String fileName);
}
