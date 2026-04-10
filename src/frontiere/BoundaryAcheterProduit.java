package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		// 1. Vérification de l'identité
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}

		// 2. Choix du produit
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);

		if (vendeurs == null || vendeurs.length == 0) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}

		// 3. Choix du vendeur
		StringBuilder question = new StringBuilder("Chez quel commerçant voulez-vous acheter des " + produit + " ?\n");
		for (int i = 0; i < vendeurs.length; i++) {
			question.append((i + 1) + " - " + vendeurs[i] + "\n");
		}
		
		int choixVendeur = Clavier.entrerEntier(question.toString());
		String vendeurChoisi = vendeurs[choixVendeur - 1];

		// 4. Déplacement à l'étal
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeurChoisi);
		System.out.println("Bonjour " + nomAcheteur);

		// 5. Achat
		int quantiteAcheter = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeurChoisi, quantiteAcheter);

		// 6. Gestion de l'affichage selon les stocks
		if (quantiteAchetee == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement il n'y en a plus !");
		} else if (quantiteAchetee < quantiteAcheter) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement " + vendeurChoisi + " n'en a plus que " + quantiteAchetee + ".");
			System.out.println(nomAcheteur + " achète tout le stock de " + vendeurChoisi + ".");
		} else {
			System.out.println(nomAcheteur + " achète " + quantiteAchetee + " " + produit + " à " + vendeurChoisi + ".");
		}
	}
}