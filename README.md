# Principe SOLID:
En POO, SOLID est un acronyme qui regroupe cinq principes de conception destinés à produire des architectures logicielles plus compréhensibles, flexibles et maintenables.

L'acronyme SOLID :

## Pourquoi SOLID?

SOLID est un ensemble de 5 bonnes pratiques dont le but est de rendre le code :
 - Plus facile à lire
 - Plus logique
 - Maintenable
 - Testable
 - Extensible

# PRINCIPE SOLID : Single responsability (la responsabilité unique)

Une classe ne doit avoir qu'une seule responsabilité et donc qu'une seule raison de changer.

## Pourquoi ?

 - Le code est beaucoup plus clair (une classe de 1000 lignes, ce n’est pas clair).
 - Chaque fichier a désormais un rôle qui lui est propre.
 - Tout le monde peut comprendre à quoi servent une classe grâce au nommage.
 - Le projet sera beaucoup plus maintenable, plus facile et agréable à faire évoluer.

## Comment faire ?


# PRINCIPE SOLID : Open/Closed

Les objets ou les entités doivent être ouverts aux extensions mais fermés aux modifications

## Pourquoi?


Dans un système, les besoins évolues parfois sans cesse. Et pour s'adapter à ces changements, on peut être tenté de modifier par exemple l'implémentation d'une classe existante. Cette façon de faire peut très vite provoquer des cassures du code, ou rendre un code illisible et difficilement maintenable.

## Comment faire?

Une bonne pratique consiste à étendre la classe concernée par les modifications afin de prendre en compte des nouveaux cas. Ainsi on applique la règle de *Ouvert* en extension et *Fermée* en modification.

*Cas d'usage*
Imaginons ce besoin: Nous voulons dans une classe une méthode responsable des calculs des aires des figures diverses(dont le type n'est pas connu à l'avance par exemple). Nous avons jusqu'ici identifié deux types de figures dont nous aimerions calculer les aires: Cercle et Rectangle. On pourrait donc être tenté d'écrire ceci: 

    public class MauvaisPattern {
		public double calculAir(Object figure) {
			if(figure instanceof Cercle c) {} 
			else if (figure instanceof Rectangle r) {}
		}	
	}

Le problème est qu'à chaque fois qu'il y aura une nouvelle figure à traiter, il faudrait modifier la classe **MauvaisPattern**.
Comment mettre en œuvre le Open/Closed principle?

# Modèle UML 


![Open-closed.png](https://draftin.com:443/images/80102?token=IxUzLXQA9QdvtNf-XD0yypuvAEDiSGxd93xdwFlqz5FJbAziYTYJVGTW1a0yrejI0-NDYI0lZeAM5hi_8qHXSxA)   
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
Tous nouvelle figure demandera juste l'implémentation de l'interface **Figure** dans une nouvelle classe. La classe Calcul restera inchangée. Et le code maintenable. En plus, on est certain que toute occurrence de paramètre de la méthode **calculAir**, aura la méthode **getAir**.

# PRINCIPE SOLID : Dependency inversion
Le module de haut niveau ne doit pas dépendre du module de bas niveau, mais qu'il doit dépendre d'abstractions.

## Pourquoi?
Le couplage fort entre deux module n'est pas une bonne pratique, car il induit une dépendance forte entre composant. La modifications d'un composant impose parfois une restructuration du code des autres composants  qui en dépendent. Ceci brise le Dependency inversion principle et Open/Closed principle.

## Comment faire?
Les classe de bas niveaux implémentent généralement la *Buisness Logic*. Et les classes de hauts niveaux sont bâties au dessus de cette couche.  L'idée consiste à inverser les habitudes, c'est-à-dire rendre la classe de bas niveau dépendante d'abstractions.
*Cas d'usage*
Imaginons ce besoin: Nous voulons implémenter quelque part dans une classe de notre projet, une méthode qui serait chargée de faire les paiements en ligne via l'API de PayPal. On serait tenté de procéder comme suit:

    public class PaiementPayPal...
    public class PaiementManager{
	    public PaiementManager(PaiementPayPal payPal){}
	    public void doPaiement(double montant, ...){
		    payPal.payer(montant, ...);
	    }
    }
    
Le problème que pose ce pattern est qu'il y'a une forte dépendance entre la classe de haut niveau **PaiementManager**  et celle de bas niveau **PaiementPayPal**. S'il y'a un nouveau mode de paiement en ligne, elle s'avèrera très vite caduque. 
Comment mettre en œuvre le Dependency inversion principle?

## Modèle UML
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

Classe qui exécute les paiements

    public class PaiementManager {
		public void doPaiement(IPaiement paiement, double montant) {
			paiement.payer(montant);
		}
	}

*Quel est l'intérêt?*
Autrefois c'était les classes de haut niveau qui devaient respecter l'implémentation des classes de bas niveau. Or avec ce pattern, ce sont les classes de bas niveau qui doivent respecter les contraintes des classes de haut niveau. Ainsi  avec ce modèle, on peut intégrer d'autres systèmes de paiements en ligne, sans modification du code existant, tout en gardant le code propre.
