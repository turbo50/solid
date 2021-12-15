package dependencyinversion;

//La classe d�pend de son abstraction (IPaiement) et non de som impl�mentation
public class PaiementManager {
	public void doPaiement(IPaiement paiement, double montant) {
		paiement.payer(montant);
	}
}
