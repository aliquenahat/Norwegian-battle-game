package fr.utt.lo02.paynatquenahat.clavier;

import fr.utt.lo02.paynatquenahat.configuration.Config;
import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.exception.*;

/**
 * Classe g�rant la saisie au clavier
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Clavier {

	/*
	 * Utiles
	 */

	/**
	 * recupere un entier tape au clavier (affiche une erreur si le contenu
	 * rentre n'est pas un nombre et redemande)
	 * 
	 * @param msg
	 *            Le message qui attends une r�ponse
	 * @return l'entier tap� au clavier
	 */
	public static int getInt(String msg) {
		int entier = 0;
		String saisie;
		boolean reussite = false;
		do {
			saisie = Clavier.getText(msg);
			try {
				entier = Integer.parseInt(saisie);
				reussite = true;
			} catch (NumberFormatException nbf) {
				Ecran.error("Veuillez entrer un nombre.");
			}
		} while (!reussite);

		return entier;
	}

	/**
	 * R�cup�re un texte au clavier
	 * 
	 * @param msg
	 *            Le message qui attends une r�ponse
	 * @return Le texte saisie
	 */
	public static String getText(String msg) {
		return Ecran.prompt(msg);
	}

	/*
	 * Specifiques
	 */

	/**
	 * Demande a l'utilisateur le nombre de joueurs pour jouer la partie
	 * 
	 * @return Le nombre de joueurs
	 * @throws InvalidNbJoueursException
	 *             Si le nombre de joueur max est d�pass�
	 */
	public static int getNbJoueurs() throws InvalidNbJoueursException {
		int nb_joueurs = Clavier
				.getInt("Veuillez choisir le nombre de joueurs (2-19): ");

		if (nb_joueurs < 2 || nb_joueurs > Config.NB_MAX_JOUEURS()) {
			throw new InvalidNbJoueursException();
		}

		return nb_joueurs;
	}

	/**
	 * Permet de choisir le nombre de cartes � poser
	 * 
	 * @param max
	 *            Le nombre max
	 * @return Le nombre de cartes � poser
	 * @throws InvalidNbCartesAPoserException
	 *             Si le nombre est invalide
	 */
	public static int getNbCartesAPoser(int max)
			throws InvalidNbCartesAPoserException {
		max = (max > 3) ? 3 : max;
		int nb_cartes = Clavier
				.getInt("Combien de cartes voulez vous poser? (1-" + max
						+ "): ");

		if (nb_cartes < 1 || nb_cartes > max) {
			throw new InvalidNbCartesAPoserException();
		}

		return nb_cartes;
	}

	/**
	 * Demande au joueur la carte qu'il souhaite jouer
	 * 
	 * @param msg
	 *            Le message de demande
	 * @return Le nom de la carte de type String
	 */
	public static String getCarteAJouer(String msg) {
		String nom_carte;
		String noms_possibles[] = { "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "valet", "dame", "roi", "as" };

		do {
			nom_carte = Clavier.getText(msg).toLowerCase();
		} while (!Clavier.carteExists(noms_possibles, nom_carte));

		return nom_carte;
	}

	/**
	 * V�rifie que la carte existe
	 * 
	 * @param array
	 *            tableau de chaines
	 * @param value
	 *            La valeur de la carte
	 * @return vrai si la carte existe et faux sinon
	 */
	public static boolean carteExists(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

}