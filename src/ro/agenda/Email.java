package ro.agenda;

public class Email {
	private String identificator;
	private ProviderEmail provider;
	
	enum ProviderEmail{
		Gmail, Yahoo, Rocketmail, Other;
	}
	
	Email(){
		this.provider = ProviderEmail.Other;
	}
	
	Email(String id){
		if (id.contains("gmail.com")){
			this.provider = ProviderEmail.Gmail;
		}
		else if (id.contains("yahoo.com")){
			this.provider = ProviderEmail.Yahoo;
		}
		else if (id.contains("rocketmail.com")){
			this.provider = ProviderEmail.Rocketmail;
		}
		else{
			this.provider = ProviderEmail.Other;
		}
		this.identificator = id;
	}

	public String getIdentificator() {
		return identificator;
	}

	public Email setIdentificator(String identificator) {
		if (identificator != null)
			this.identificator = identificator;
		updateProvider();
		return this;
	}

	public ProviderEmail getProvider() {
		return provider;
	}

	public Email setProvider(ProviderEmail provider) {
		if (provider != null)
			this.provider = provider;
		return this;
	}
	
	public void updateProvider(){
		if (this.identificator.contains("gmail.com")){
			this.provider = ProviderEmail.Gmail;
		}
		else if (this.identificator.contains("yahoo.com")){
			this.provider = ProviderEmail.Yahoo;
		}
		else if (this.identificator.contains("rocketmail.com")){
			this.provider = ProviderEmail.Rocketmail;
		}
		else{
			this.provider = ProviderEmail.Other;
		}
		return;
	}
	
	@Override
	public String toString() {
		return Controller.toNonEmpty(this.getIdentificator());
	}
}
