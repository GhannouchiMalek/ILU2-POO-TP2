package controleur;

import villagegaulois.Etal;

public class ControlLibererEtal {
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	public ControlLibererEtal(
			ControlTrouverEtalVendeur controlTrouverEtalVendeur) {
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean isVendeur(String nomVendeur) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		return etal != null;
	}

	/**
	 * * @param nomVendeur
	 * @return donneesEtal est un tableau de chaine contenant
	 * [0] : un boolean indiquant si l'étal est occupé
	 * [1] : nom du vendeur
	 * [2] : produit vendu
	 * [3] : quantité de produit à vendre au début du marché
	 * [4] : quantité de produit vendu
	 */
	public String[] libererEtal(String nomVendeur) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		String[] donneesEtal = null;
		
		if (etal != null) {
			// On récupère les données AVANT de libérer l'étal
			donneesEtal = etal.etatEtal();
			// On libère l'étal
			etal.libererEtal();
		}
		
		return donneesEtal;
	}

}