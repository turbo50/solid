package openclosed;

//Classe ouverte aux extensions (Open) et ferm�e aux modifications
public class Calcul {
	//Utilisation de l'interface au lieu de l'h�ritage
	private Figure maFigure;
	
	public Calcul(Figure maFigure) {
		super();
		this.maFigure = maFigure;
	}

	public double calculAir() {
		return maFigure.getAir();
	}
}
