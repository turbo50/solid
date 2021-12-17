package liskovsubstitution;

import java.time.LocalDate;

public class Personne {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public void dormir() {
        System.out.println("Je dors pour me reposer");
    }
}
