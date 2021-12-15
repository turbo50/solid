package dependencyinversion;

public class PaiementMobileMoney implements IPaiement {

	@Override
	public void payer(double montant) {
		System.out.println("Paiement par Mobile Money. Montant = " + montant);
	}

}
