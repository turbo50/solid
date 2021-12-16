
# PRINCIPE SOLID : Open/Closed

Les objets ou les entit�s doivent �tre ouverts aux extensions mais ferm�s aux modifications

## Pourquoi?


Dans un syst�me, les besoins �volues parfois sans cesse. Et pour s'adapter � ces changements, on peut �tre tent� de modifier par exemple l'impl�mentation d'une classe existante. Cette fa�on de faire peut tr�s vite provoquer des cassures du code, ou rendre un code illisible et difficilement maintenable.

## Comment faire?

Une bonne pratique consiste � �tendre la classe concern�e par les modifications afin de prendre en compte des nouveaux cas. Ainsi on applique la r�gle de Ouvert en extension et ferm�e en modification.

Cas d'usage
Imaginons ce besoin: Nous voulons dans une classe une m�thode responsable des calculs des aires des figures diverses(dont le type n'est pas connu � l'avance par exemple). Nous avons jusqu'ici identifi� deux types de figures dont nous aimerions calculer les aires: Cercle et Rectangle. On pourrait donc �tre tenter d'�crire ceci: 

    public class MauvaisPattern {
		public double calculAir(Object figure) {
			if(figure instanceof Cercle c) {} 
			else if (figure instanceof Rectangle r) {}
		}	
	}

Le probl�me est qu'� chaque fois qu'il y aura une nouvelle figure � traiter, il faudrait modifier la classe **MauvaisPattern**.
Comment mettre en �uvre le Open/Closed principle?

# Mod�le UML 

![Open-closed.png](https://draftin.com:443/images/80100?token=5vmtM-4Ol--3xl7aj5gna2E1vjAdZ_5odTROIhFW904L_toNL-5UpsUhfbtEiK2mKe1H4HhAY0Arp1FJBp0tKlA) 
## Interface Figure

 
C'est l'interface qui est ferm� en modification (closed)

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
Classe o� se font les calculs

    public class Calcul {
		public double calculAir(Figure maFigure) {
			return maFigure.getAir();
		}
	}
Tous nouvelle figure demandera juste l'impl�mentation de l'interface **Figure** dans une nouvelle classe. La classe Calcul restera inchang�e. Et le code maintenable.

