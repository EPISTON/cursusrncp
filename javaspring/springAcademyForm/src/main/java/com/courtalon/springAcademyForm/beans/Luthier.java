package com.courtalon.springAcademyForm.beans;

import java.util.List;
import java.util.Random;

public class Luthier {
	
	private List<String> sonorites;

	public List<String> getSonorites() {
		return sonorites;
	}

	public void setSonorites(List<String> sonorites) {
		this.sonorites = sonorites;
	}
	
	public IInstrument fabriqueInstrument(){
		Violon v = new Violon();
		Random rd = new Random();
		v.setSonorite(sonorites.get(rd.nextInt(sonorites.size())));
		return v;
	}

}
