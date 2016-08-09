package javaPatternComposite;

import java.util.ArrayList;
import java.util.List;

public class MediaElement {
	private List<MediaElement> childs;
	
	public void addChild(MediaElement me) {
		childs.add(me);
	}
	public void removeElement(MediaElement me) {
		childs.remove(me);
	}
	
	public MediaElement() {
		childs = new ArrayList<>();
	}
	
	public void afficher() {
		for (MediaElement me : childs) {
			me.afficher();
		}
	}
	
}
