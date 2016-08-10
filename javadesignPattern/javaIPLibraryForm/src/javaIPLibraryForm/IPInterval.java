package javaIPLibraryForm;

import java.util.Iterator;

public class IPInterval implements Iterable<IPAdress>
{
	private IPAdress debut;
	private IPAdress fin;
	
	public IPAdress getDebut() {
		return debut;
	}
	public void setDebut(IPAdress debut) {
		this.debut = debut;
	}
	public IPAdress getFin() {
		return fin;
	}
	public void setFin(IPAdress fin) {
		this.fin = fin;
	}
	
	public IPInterval(IPAdress debut, IPAdress fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return "IPInterval [debut=" + debut + ", fin=" + fin + "]";
	}

	@Override
	public Iterator<IPAdress> iterator() {
		return new IPIntervalIterator();
	}
	
	
	public class IPIntervalIterator implements Iterator<IPAdress> {
		private IPAdress currentIP;
		
		public IPIntervalIterator() {
			currentIP = debut.predecessor();
		}
		
		@Override
		public boolean hasNext() {
			// si mon adresse est avant l'adresse de fin, le parcours peu continuer
			if (currentIP.compareTo(fin) == -1)
				return true;
			else
				return false;
		}

		@Override
		public IPAdress next() {
			currentIP = currentIP.successor();
			return currentIP;
		}
		
	}
	
	
}
