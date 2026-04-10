package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlPrendreEtal {
	private Village village;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlPrendreEtal(ControlVerifierIdentite controlVerifierIdentite,
			Village village) {
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.village = village;
	}

	public boolean resteEtals() {
		return village.rechercherEtalVide();
	}

	public int prendreEtal(String nomVendeur, String produit, int nbProduit) {
		int numeroEtal = -1;
		Gaulois gaulois = village.trouverHabitant(nomVendeur);
		
		if (gaulois != null) {
			numeroEtal = village.installerVendeur(gaulois, produit, nbProduit);
		}
		
		// On transforme l'index du tableau (0 à 4) en numéro d'étal pour l'utilisateur (1 à 5)
		if (numeroEtal != -1) {
			return numeroEtal + 1;
		}
		
		return numeroEtal;
	}

	public boolean verifierIdentite(String nomVendeur) {
		return controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
}