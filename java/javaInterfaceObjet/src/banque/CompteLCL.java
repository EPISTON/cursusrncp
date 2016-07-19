package banque;

public class CompteLCL extends LclEntities implements ICompteBancaire {

	private double solde;
	private String noIban;
	
	
	public String getNoIban() {return noIban;}
	public void setNoIban(String noIban) {this.noIban = noIban;}
	public void setSolde(double solde) {this.solde = solde;}

	@Override
	public double getSolde() {return solde;}

	@Override
	public boolean retirer(double montant) {
		if (montant < getSolde() + 200.0) {
			setSolde(getSolde() - montant);
			return true;
		}
		return false;
	}

	@Override
	public void deposer(double montant) {
		setSolde(getSolde() + montant);
	}
	
	public CompteLCL(String enititiesNumber, double solde, String noIban) {
		super(enititiesNumber);
		this.solde = solde;
		this.noIban = noIban;
	}
	@Override
	public String toString() {
		return "CompleLCL [solde=" + solde + ", noIban=" + noIban + ", getEnititiesNumber()=" + getEnititiesNumber()
				+ "]";
	}

	@Override
	public int compareTo(ICompteBancaire o) {
		if (getSolde() > o.getSolde())
			return 1;
		if (getSolde() < o.getSolde())
			return -1;
		return 0;
	}		

	
	
}
