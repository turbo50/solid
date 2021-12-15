package openclosed;

//Classe ouverte aux extensions (Open) et fermée aux modifications
public class Calcul {
	//Utilisation de l'interface au lieu de l'héritage
	private Figure maFigure;
	
	public Calcul(Figure maFigure) {
		super();
		this.maFigure = maFigure;
	}

	public double calculAir() {
		return maFigure.getAir();
	}
}
