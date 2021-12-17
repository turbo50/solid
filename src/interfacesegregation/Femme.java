package interfacesegregation;

public class Femme implements Personne, Action {
    @Override
    public void accoucher() {
        System.out.println("J'accouche 9 mois après fécondation");
    }

}
