package fr.utt.lo02.paynatquenahat.paquet;

import fr.utt.lo02.paynatquenahat.affichage.Ecran;
import fr.utt.lo02.paynatquenahat.cartes.*;
import fr.utt.lo02.paynatquenahat.exception.*;

/**
 * Classe de gestion des pioches
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Pioche extends Paquet {

	// attributs
	private static Pioche _instance;

	/*
	 * Singleton
	 */

	/**
	 * Permet de ne récuperer qu'une seule instance de la pioche pendant toute
	 * la partie
	 * 
	 */
	private Pioche() {
	}

	/**
	 * 
	 * @return L'instance de la pioche
	 */
	public static Pioche getInstance() {
		if (_instance == null) {
			_instance = new Pioche();
		}
		return _instance;
	}

	/**
	 * Récupère la carte sur le dessus de la
	 * 
	 * @return La carte à prendre
	 */
	public Carte piocherCarte() {
		Carte carte_a_prendre = null;
		try {
			// on retire la carte du paquet
			carte_a_prendre = this.retirerCarte(this.cartes.size() - 1);
		} catch (CarteNotFoundException e) {
			Ecran.bug(e.getMessage());
		}
		return carte_a_prendre;
	}

}