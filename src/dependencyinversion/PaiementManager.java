package dependencyinversion;

//La classe dépend de son abstraction (IPaiement) et non de som implémentation
public class PaiementManager {
	public void doPaiement(IPaiement paiement, double montant) {
		paiement.payer(montant);
	}
}
