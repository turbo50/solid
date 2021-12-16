package openclosed;

//Ouverte à l'extension - Open
public class Cercle implements Figure {
	private double rayon;
	
	public Cercle(double rayon) {
		super();
		this.rayon = rayon;
	}
	
	@Override
	public double getAir() {
		return Math.PI * Math.pow(rayon, 2);
	}
}
