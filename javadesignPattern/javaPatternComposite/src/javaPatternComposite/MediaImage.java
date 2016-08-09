package javaPatternComposite;

public class MediaImage extends MediaElement {
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MediaImage(String imageUrl) {
		super();
		this.imageUrl = imageUrl;
	}

	@Override
	public void afficher() {
		System.out.println(" image=" + getImageUrl() + " ");
		super.afficher();
	}
	
	
}
