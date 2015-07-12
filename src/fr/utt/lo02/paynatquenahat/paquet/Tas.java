package fr.utt.lo02.paynatquenahat.paquet;

import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.cartes.*;
import fr.utt.lo02.paynatquenahat.exception.*;

/**
 * Classe de gestion des tas
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Tas extends Paquet {

	// attributs
	private static Tas _instance;

	/*
	 * Singleton
	 */

	/**
	 * permet de ne recuperer qu'une seule instance du tas pendant toute la
	 * partie
	 */
	private Tas() {
	}

	/**
	 * 
	 * @return L'instance du Tas
	 */
	public static Tas getInstance() {
		if (_instance == null) {
			_instance = new Tas();
		}
		return _instance;
	}

	/**
	 * Envoyer le tas a un des joueurs de la partie permet aussi au joueur qui
	 * ne peut pas jouer de recuperer tout le tas (on l'envoie au joueur qui
	 * joue)
	 * 
	 * @param j
	 *            Le joueur qui recoit le tas
	 */
	public void envoyerTas(Joueur j) {
		try {
			while (!this.cartes.isEmpty()) {
				Carte c = this.retirerCarte(0);
				j.donnerCarteMain(c);
			}
		} catch (CarteNotFoundException e) {
			Ecran.error(e.getMessage());
		}
	}

	/**
	 * Retire les cartes du tas apres qu'un joueur ait posé un 10
	 */
	public void viderTas() {
		try {
			while (!this.cartes.isEmpty()) {
				this.retirerCarte(0);
			}
		} catch (CarteNotFoundException e) {
			Ecran.error(e.getMessage());
		}
	}

	/**
	 * Recupère la dernière carte du tas pour savoir si un joueur peut jouer par
	 * dessus
	 * 
	 * @return La dernière carte du tas (null si pas de tas)
	 */
	public Carte getDerniereCarte() {
		if (this.cartes.size() > 0) {
			return this.cartes.get(this.cartes.size() - 1);
		}
		return null;
	}

}