package singleResponsability;
import java.util.Date;

public class Employee {
    private Long id;
    private String nom;
    private String departement;
    private Date dateEmbauche;

    public Employee(String nom,String departement,Date dateEmbauche){
        this.nom=nom;
        this.departement=departement;
        this.dateEmbauche=dateEmbauche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }


    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", departement='" + departement + '\'' +
                ", dateEmbauche='" + dateEmbauche + '\'' +
                '}';
    }
}
