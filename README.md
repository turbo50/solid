
# PRINCIPE SOLID : Open/Closed

Les objets ou les entités doivent être ouverts aux extensions mais fermés aux modifications

## Pourquoi?


Dans un système, les besoins évolues parfois sans cesse. Et pour s'adapter à ces changements, on peut être tenté de modifier par exemple l'implémentation d'une classe existante. Cette façon de faire peut très vite provoquer des cassures du code, ou rendre un code illisible et difficilement maintenable.

## Comment faire?

Une bonne pratique consiste à étendre la classe concernée par les modifications afin de prendre en compte des nouveaux cas. Ainsi on applique la règle de Ouvert en extension et fermée en modification.

Cas d'usage
Imaginons ce besoin: Nous voulons dans une classe une méthode responsable des calculs des aires des figures diverses(dont le type n'est pas connu à l'avance par exemple). Nous avons jusqu'ici identifié deux types de figures dont nous aimerions calculer les aires: Cercle et Rectangle. On pourrait donc être tenter d'écrire ceci: 

    public class MauvaisPattern {
		public double calculAir(Object figure) {
			if(figure instanceof Cercle c) {} 
			else if (figure instanceof Rectangle r) {}
		}	
	}

Le problème est qu'à chaque fois qu'il y aura une nouvelle figure à traiter, il faudrait modifier la classe **MauvaisPattern**.
Comment mettre en œuvre le Open/Closed principle?

# Modèle UML 

![Open-closed.png](https://draftin.com:443/images/80100?token=5vmtM-4Ol--3xl7aj5gna2E1vjAdZ_5odTROIhFW904L_toNL-5UpsUhfbtEiK2mKe1H4HhAY0Arp1FJBp0tKlA) 
## Interface Figure

 
C'est l'interface qui est fermé en modification (closed)

    package openclosed;
    public interface Figure {
	    public double getAir();
	}


## Classe Cercle

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
## Classe Rectangle

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
Classe où se font les calculs

    public class Calcul {
		public double calculAir(Figure maFigure) {
			return maFigure.getAir();
		}
	}
Tous nouvelle figure demandera juste l'implémentation de l'interface **Figure** dans une nouvelle classe. La classe Calcul restera inchangée. Et le code maintenable.

