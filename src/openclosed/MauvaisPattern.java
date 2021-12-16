package openclosed;

public class MauvaisPattern {
	public double calculAir(Object figure) {
		if(figure instanceof Cercle c) {
			return 0;
		} 
		else if (figure instanceof Rectangle r) {
			return 0;
		}
		else
			return 0;
	}	
}
