package ro.agenda;

public class Telefon {
	private String numarTelefon;
	private ProviderTelefonie provider;
	enum ProviderTelefonie{
		Orange, Vodafone, Telekom, DigiMobil, Other;
	}
	
	Telefon(){
		this.provider = ProviderTelefonie.Other;
	}
	
	Telefon(String numar){
		if (numar.startsWith("072") || numar.startsWith("073") ||
				numar.startsWith("+4072") || numar.startsWith("+4073")){
			this.provider = ProviderTelefonie.Vodafone;
		}
		else if (numar.startsWith("074") || numar.startsWith("075") || 
				numar.startsWith("+4074") || numar.startsWith("+4075")){
			this.provider = ProviderTelefonie.Orange;
		}
		else if (numar.startsWith("076") || numar.startsWith("+4076")){
			this.provider = ProviderTelefonie.Telekom;
		}
		else if (numar.startsWith("077") || numar.startsWith("+4077")){
			this.provider = ProviderTelefonie.DigiMobil;
		}
		else{
			this.provider = ProviderTelefonie.Other;
		}
		this.numarTelefon = numar;
	}

	public String getNumarTelefon() {
		return numarTelefon;
	}

	public Telefon setNumarTelefon(String numarTelefon) {
		if (numarTelefon != null)
			this.numarTelefon = numarTelefon;
		updateProvider();
		return this;
	}

	public ProviderTelefonie getProvider() {
		return provider;
	}

	public Telefon setProvider(ProviderTelefonie provider) {
		if (provider != null)
			this.provider = provider;
		return this;
	}
	
	public void updateProvider(){
		if (this.numarTelefon.startsWith("072") || this.numarTelefon.startsWith("073") ||
				this.numarTelefon.startsWith("+4072") || this.numarTelefon.startsWith("+4073")){
			this.provider = ProviderTelefonie.Vodafone;
		}
		else if (this.numarTelefon.startsWith("074") || this.numarTelefon.startsWith("075") || 
				this.numarTelefon.startsWith("+4074") || this.numarTelefon.startsWith("+4075")){
			this.provider = ProviderTelefonie.Orange;
		}
		else if (this.numarTelefon.startsWith("076") || this.numarTelefon.startsWith("+4076")){
			this.provider = ProviderTelefonie.Telekom;
		}
		else if (this.numarTelefon.startsWith("077") || this.numarTelefon.startsWith("+4077")){
			this.provider = ProviderTelefonie.DigiMobil;
		}
		else{
			this.provider = ProviderTelefonie.Other;
		}
		return;
	}

	@Override
	public String toString() {
		return Controller.toNonEmpty(this.getNumarTelefon());
	}
	
}
