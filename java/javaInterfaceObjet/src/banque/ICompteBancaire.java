package banque;

public interface ICompteBancaire extends Comparable<ICompteBancaire>
{

	double getSolde();
	boolean retirer(double montant);
	void deposer(double montant);
}
