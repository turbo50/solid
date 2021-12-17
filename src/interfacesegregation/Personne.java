package interfacesegregation;

import java.time.LocalDate;

public interface Personne {

    public default void dormir() {
        System.out.println("Je dors pour me reposer");
    }
}
