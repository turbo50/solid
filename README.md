# Principe SOLID:
En POO, SOLID est un acronyme qui regroupe cinq principes de conception destinés à produire des architectures logicielles plus compréhensibles, flexibles et maintenables.
 - S : principe de la responsabilité unique
 - O : principe ouvert/fermé
 - L : principe de substitution de Liskov
 - I : principe de ségrégation des interfaces
 - D : principe d'inversion des dépendances

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

![cycle_srp drawio (1)](https://user-images.githubusercontent.com/7405676/146512910-47100578-5e84-4424-8312-0eab441db767.png)

Le schéma ci-dessus montre que le principe n'a pas été respecté  et pour appliqué les bonnes pratiques et respecter le principe SOLID et plus précisement le S (responsabilité unique), notre class doit avoir qu'une seule respnsabilité (voir schéma ci-dessous).

![cycle_srp drawio (2)](https://user-images.githubusercontent.com/7405676/146515909-6c1ab682-f225-43aa-9b31-fd4c7db1d75a.png)

On a divisé notre classe initiale qui avait deux responsabilités en deux classes, chacune ayant sa propre responsabilité.

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


![Open-closed.png](https://draftin.com:443/images/80113?token=Vtwyeq_hwUzfxvDMzTrgRZBfQSZiN_0nlkWhkAg_Jdv_7YhIvr-2RKkwTpxNVwJIz7ccjZEpZ6Khp2oyGtOIm9Q)    
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
Toute nouvelle figure demandera juste l'implémentation de l'interface **Figure** dans une nouvelle classe. La classe Calcul restera inchangée. Et le code maintenable. En plus, on est certain que toute occurrence de paramètre de la méthode **calculAir**, aura la méthode **getAir**.

# PRINCIPE SOLID : Liskov Substitution

Une superclasse doit pouvoir être instanciée par n'importe qu'elle héritière sans que cela ne pose de problème.
## Pourquoi?


Le principe de substitution de Liskov reprend le principe Open/Closed et l'applique au cas particulier de l'héritage de classes : si une classe enfant est une implémentation valide, alors une classe parent doit également l'être (et vice versa) ;
## Comment faire?

Une bonne pratique consiste à envisager des interfaces de haut niveau avant les implémentations de bas niveau (concrètes).
*Cas d'usage*
Nous avons une classe Personne et des classe héritières Homme et Femme. On pourrait être tenté de faire ceci:

    public class Personne {
	    private String nom;
        private String prenom;
        private LocalDate dateNaissance;
        public void dormir(){
            System.out.println("Je dors pour me reposer");
        ;
        public void accoucher(){
            System.out.println("Je prend 9 mois pour accoucher");
	}

    public class Homme extends Personne{
    }

    public class Femme extends Personne{
    }

Le problème ici est qu'un Homme n'accouche pas. Une Personne ne peut donc pas être substituée par un Homme et cela constitue une violation du principe "Liskov Substitution principle"  **MauvaisPattern**.
Comment mettre en œuvre Liskov Substitution principle ?

# Modèle UML


![Diagramme de classe --- Liskov](https://user-images.githubusercontent.com/39199107/146538667-73e8326f-f6b8-4de9-a2ba-607a98ecc123.PNG)
## Superclasse Personne


La superclasse Personne ne conserve que les propriétés et méthodes communes à toutes les héritières

    package liskovsubstitution;
    public class Personne {
	    private String nom;
        private String prenom;
        private LocalDate dateNaissance;
        public void dormir(){
            System.out.println("Je dors pour me reposer");
        ;
	    }
    }


## Classe Homme

    public class Homme extends Personne{
    }
## Classe Femme

    public class Femme extends Personne implements Action{
        @Override
        public void accoucher(){
            System.out.println("J'accouche 9 mois après fécondation");
        }
    }


## Interface Action

    Public interface Action{
        public void accoucher();
    }
Avec ce modèle la classe Personne peut être instanciée par l'ensemble de ses héritières sans que cela ne cause de problème. Le principe de Liskov Substitution est bien respecté.

# PRINCIPE SOLID : Interface Segregation

Une classe qui implemente une interface ou une intefarce qui en étend une autre doit nécessiter tous les contrats de cette interface. En d'autre terme toutes les méthodes d'une interface doivent être utile à la classe qui l'implémente ou l'interface qui l'étend
## Pourquoi?


Le principe de segregation des interface reprend le principe Open/Closed et l'applique au cas particulier de l'héritage de classes : si une classe enfant est une implémentation valide, alors une classe parent doit également l'être (et vice versa) ;
## Comment faire?

Une bonne pratique consiste à envisager des interfaces de haut niveau avant les implémentations de bas niveau (concrètes).
*Cas d'usage*
Nous avons une classe Personne et des classe héritières Homme et Femme. On pourrait être tenté de faire ceci:

    public interface Personne {
        public default void dormir(){
            System.out.println("Je dors pour me reposer");
        ;
        public void accoucher(){
            System.out.println("Je prend 9 mois pour accoucher");
	}

    public class Homme implements Personne{
        @Override
        public void accoucher(){
            //Ne rien faire
        }
    }

    public class Femme implements Personne{
        @Override
        public void accoucher(){
            System.out.println("J'accouche 9 mois après fécondation");
        }
    }

Le problème ici est qu'un Homme n'accouche pas. La classe homme n'aura donc pas d'implémentation possible de la méthode accoucher() qu'elle se doit d'implémenter et cela constitue une violation du principe de segrégation des interfaces qui stipule que toute classe doit nécessairement implémenter toutes les méthodes abstraites reçues d'une interface **MauvaisPattern**.
Comment mettre en œuvre Interface Segregation principle ?

# Modèle UML


![Diagramme de classe --- Interface S](https://user-images.githubusercontent.com/39199107/146551652-f8fc6b83-cb64-49f2-8d55-06017ab18f45.PNG)
## Interface Personne


L'interface Personne ne conserve que les méthodes communes à toutes les classes qui l'implémente

    package interfacesegregation;
    public interface Personne {
        public default void dormir(){
            System.out.println("Je dors pour me reposer");
	    }
    }


## Classe Homme

    public class Homme implements Personne{
        
    }
## Classe Femme

    public class Femme implements Personne, Action{
        @Override
        public void accoucher(){
            System.out.println("J'accouche 9 mois après fécondation");
        }
    }


## Interface Action

    Public interface Action{
        public void accoucher();
    }
Avec ce modèle les classes Homme et Femme implémente l'ensemble des méthodes des interface qu'elles implémentent.
L'interface Personne à été remplacé par deux interface de manière à respecter le principe.

# PRINCIPE SOLID : Dependency inversion
Le module de haut niveau ne doit pas dépendre directement du module de bas niveau, mais doit dépendre d'abstractions.

## Pourquoi?
Le couplage fort entre deux modules n'est pas une bonne pratique, car il induit une dépendance forte entre composant. La modifications d'un composant impose parfois une restructuration du code des autres composants  qui en dépendent. Ceci brise le Dependency inversion principle et Open/Closed principle.

## Comment faire?
Les classes de bas niveau implémentent généralement la *Buisness Logic*. Et les classes de haut niveau sont bâties au dessus de cette couche.  L'idée consiste à inverser les habitudes, c'est-à-dire rendre la classe de bas niveau dépendante d'abstractions.
*Cas d'usage*
Imaginons ce besoin: Nous voulons implémenter quelque part dans une classe de notre projet, une méthode qui serait chargée de faire les paiements en ligne via l'API de PayPal. On serait tenté de procéder comme suit:

    public class PayPal{
    	...
    	public void payer(double montant){}
    }
    public class PaiementManager{
        private PayPal payPal;
	    public PaiementManager(PayPal payPal){}
	    public void doPaiement(double montant){
		    payPal.payer(montant);
	    }
    }
    
Le problème que pose ce pattern est qu'il y'a une forte dépendance entre la classe de haut niveau **PaiementManager**  et celle de bas niveau **PaiementPayPal**. S'il faut prendre en compte un nouveau mode de paiement en ligne, elle s'avèrera très vite inutilisable. 
Comment mettre en œuvre le Dependency inversion principle?

## Modèle UML
![dependency-inversion.png](https://draftin.com:443/images/80112?token=DDZnx3U744MEG-XGM6_9g49pbONZsQBJFAH4EYdUQQIbyyQJXCT8gJI-2gcQLWAVPABm2dogFPL_MxyKiBpxAlo) 

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
Autrefois c'étaient les classes de haut niveau qui devaient respecter l'implémentation des classes de bas niveau. Or avec ce pattern, ce sont les classes de bas niveau qui doivent respecter les contraintes des classes de haut niveau. Ainsi  avec ce modèle, on peut intégrer d'autres systèmes de paiements en ligne, sans modification du code existant, tout en gardant le code propre.
