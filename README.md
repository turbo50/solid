
# PRINCIPE SOLID : Open/Closed

Les objets ou les entit�s doivent �tre ouverts aux extensions mais ferm�s aux modifications

## Pourquoi?


Dans un syst�me, les besoins �volues parfois sans cesse. Et pour s'adapter � ces changements, on peut �tre tent� de modifier par exemple l'impl�mentation d'une classe existante. Cette fa�on de faire peut tr�s vite provoquer des cassures du code, ou rendre un code illisible et difficilement maintenable.

## Comment faire?

Une bonne pratique consiste � �tendre la classe concern�e par les modifications afin de prendre en compte des nouveaux cas. Ainsi on applique la r�gle de *Ouvert* en extension et *Ferm�e* en modification.

*Cas d'usage*
Imaginons ce besoin: Nous voulons dans une classe une m�thode responsable des calculs des aires des figures diverses(dont le type n'est pas connu � l'avance par exemple). Nous avons jusqu'ici identifi� deux types de figures dont nous aimerions calculer les aires: Cercle et Rectangle. On pourrait donc �tre tent� d'�crire ceci: 

    public class MauvaisPattern {
		public double calculAir(Object figure) {
			if(figure instanceof Cercle c) {} 
			else if (figure instanceof Rectangle r) {}
		}	
	}

Le probl�me est qu'� chaque fois qu'il y aura une nouvelle figure � traiter, il faudrait modifier la classe **MauvaisPattern**.
Comment mettre en �uvre le Open/Closed principle?

# Mod�le UML 


![Open-closed.png](https://draftin.com:443/images/80102?token=IxUzLXQA9QdvtNf-XD0yypuvAEDiSGxd93xdwFlqz5FJbAziYTYJVGTW1a0yrejI0-NDYI0lZeAM5hi_8qHXSxA)   
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
Tous nouvelle figure demandera juste l'impl�mentation de l'interface **Figure** dans une nouvelle classe. La classe Calcul restera inchang�e. Et le code maintenable. En plus, on est certain que toute occurrence de param�tre de la m�thode **calculAir**, aura la m�thode **getAir**.

# PRINCIPE SOLID : Dependency inversion
Le module de haut niveau ne doit pas d�pendre du module de bas niveau, mais qu'il doit d�pendre d'abstractions.

## Pourquoi?
Le couplage fort entre deux module n'est pas une bonne pratique, car il induit une d�pendance forte entre composant. La modifications d'un composant impose parfois une restructuration du code des autres composants  qui en d�pendent. Ceci brise le Dependency inversion principle et Open/Closed principle.

## Comment faire?
Les classe de bas niveaux impl�mentent g�n�ralement la *Buisness Logic*. Et les classes de hauts niveaux sont b�ties au dessus de cette couche.  L'id�e consiste � inverser les habitudes, c'est-�-dire rendre la classe de bas niveau d�pendante d'abstractions.
*Cas d'usage*
Imaginons ce besoin: Nous voulons impl�menter quelque part dans une classe de notre projet, une m�thode qui serait charg�e de faire les paiements en ligne via l'API de PayPal. On serait tent� de proc�der comme suit:

    public class PaiementPayPal...
    public class PaiementManager{
	    public PaiementManager(PaiementPayPal payPal){}
	    public void doPaiement(double montant, ...){
		    payPal.payer(montant, ...);
	    }
    }
    
Le probl�me que pose ce pattern est qu'il y'a une forte d�pendance entre la classe de haut niveau **PaiementManager**  et celle de bas niveau **PaiementPayPal**. S'il y'a un nouveau mode de paiement en ligne, elle s'av�rera tr�s vite caduque. 
Comment mettre en �uvre le Dependency inversion principle?

## Mod�le UML
![dependency-inversion.png](https://draftin.com:443/images/80104?token=LSA6cu_yXQxV7EJmveeER-zGJeo8BMK3HB5Ah9-RDuS97ClXgxm-09M393Sz6oMJFXA6UwbWIIUaK7CBriTFjgY) 

## Interface IPaiement

    public interface IPaiement {
		public void payer(double montant);
	}
## Classe PaiementPayPal

    public class PaiementPayPal implements IPaiement {
		@Override
		public void payer(double montant) {
			System.out.println("Paiement par PayPal. Montant = " + montant);
		}
	}
## Classe PaiementMobileMoney

    public class PaiementMobileMoney implements IPaiement {
		@Override
		public void payer(double montant) {
			System.out.println("Paiement par Mobile Money. Montant = " + montant);
		}
	}

Classe qui ex�cute les paiements

    public class PaiementManager {
		public void doPaiement(IPaiement paiement, double montant) {
			paiement.payer(montant);
		}
	}

*Quel est l'int�r�t?*
Autrefois c'�tait les classes de haut niveau qui devaient respecter l'impl�mentation des classes de bas niveau. Or avec ce pattern, ce sont les classes de bas niveau qui doivent respecter les contraintes des classes de haut niveau. Ainsi  avec ce mod�le, on peut int�grer d'autres syst�mes de paiements en ligne, sans modification du code existant, tout en gardant le code propre.
