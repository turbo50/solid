package dependencyinversion;

public class PaiementPayPal implements IPaiement {
	@Override
	public void payer(double montant) {
		System.out.println("Paiement par PayPal. Montant = " + montant);
	}
}
