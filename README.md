# solid
Explications des principes SOLID par des exemples

# PRINCIPE SOLID : Open/Closed

Les objets ou les entités doivent être ouverts aux extensions mais fermés aux modifications


# Modèle UML
![enter image description here](https://drive.google.com/file/d/1csd_9asbmrHiBy5AUEDz2MbJBuwzlz1c/view?usp=sharing)

## Interface Figure

 
C'est l'interface qui est fermé en modification (closed)

    package openclosed;
    public interface Figure {
	    public double getAir();
	}

    


## Classe Cercle



