package openclosed;

//Ouverte à l'extension - Open
public class Rectangle implements Figure {
	private double longeur;
	private double largeur;
	
	
	public Rectangle(double longeur, double largeur) {
		super();
		this.longeur = longeur;
		this.largeur = largeur;
	}


	@Override
	public double getAir() {
		return longeur * largeur;
	}

}
