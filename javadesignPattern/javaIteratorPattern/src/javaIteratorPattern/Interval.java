package javaIteratorPattern;

import java.util.Iterator;

public class Interval implements Iterable<Integer>
{
	private int debut;
	private int fin;
	
	public int getDebut() {
		return debut;
	}
	public void setDebut(int debut) {
		this.debut = debut;
	}
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	public Interval(int debut, int fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return "Interval [debut=" + debut + ", fin=" + fin + "]";
	}
	@Override
	public Iterator<Integer> iterator() {
		return new IntervalIterator();
	}
	
	public class IntervalIterator implements Iterator<Integer>
	{
		private int position;
		
		public IntervalIterator() {
			position = debut - 1;
		}
		@Override
		public boolean hasNext() {
			return (position < fin);
		}
		@Override
		public Integer next() {
			return ++position;
		}
		
	}
	

}
