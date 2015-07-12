package fr.utt.lo02.paynatquenahat.cartes;

import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.partie.*;
import fr.utt.lo02.paynatquenahat.paquet.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;

/**
 * Classe Mère Carte qui représente une carte
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Carte {

	// attributs
	private int valeur;
	private String nom;
	private Enseigne enseigne;

	/*
	 * CONSTRUCTEUR
	 */
	/**
	 * Créé la carte directement avec le constructeur avec toutes les infos
	 * 
	 * @param value
	 *            La valeur de la carte
	 * @param name
	 *            Le nom de la carte
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public Carte(int value, String name, String enseigne) {
		/*
		 * On dresse la liste des enseignes possibles et la liste des noms de
		 * carte possibles afin de s'asurer que l'on a pas entre un mauvais nom
		 * lors de la creation de l'objet
		 */
		String enseignes_possibles[] = { "Carreau", "Coeur", "Pique", "Trefle" };
		String noms_possibles[] = { "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "Valet", "Dame", "Roi", "As" };

		// Enseigne valide ?
		if (this.positionInArray(enseignes_possibles, enseigne) >= 0) {
			switch (enseigne) {
			case "Carreau":
				this.enseigne = Enseigne.carreau;
				break;
			case "Coeur":
				this.enseigne = Enseigne.coeur;
				break;
			case "Pique":
				this.enseigne = Enseigne.pique;
				break;
			case "Trefle":
				this.enseigne = Enseigne.trefle;
			}
		} else {
			Ecran.bug("\"" + enseigne
					+ "\" n'est pas un nom d'enseigne valide.");
		}
		// Valeur de carte valide ?
		if (value >= 2 && value <= 14) {
			this.valeur = value;
		} else {
			Ecran.bug("\"" + value + "\" n'est pas une valeur de carte valide.");
		}
		// Nom de carte valide ?
		int index = this.positionInArray(noms_possibles, name);
		if (index >= 0) {
			this.nom = name;

			// Valeur et nom de carte correspondants ?
			if (value != index + 2) {
				Ecran.bug("La valeur \"" + value
						+ "\" ne correspond pas a la carte \"" + name + "\".");
			}
		} else {
			Ecran.bug("\"" + name + "\" n'est pas un nom de carte valide.");
		}
	}

	/**
	 * Retourne la position d'une chaine de caracteres dans un tableau de type
	 * String
	 * 
	 * @return la position dans le tableau et -1 si la chaine n'est pas contenue
	 *         dans le tableau
	 */
	public int positionInArray(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * JOUER LA CARTE
	 */

	/**
	 * Permet de savoir si la carte donnee en parametre peut etre jouee par
	 * dessus la carte depuis laquelle est appelee la methode
	 * 
	 * @return vrai si la carte est jouable
	 */
	protected boolean jouableParDessus(Carte carte) {
		if (this.getValeur() <= carte.getValeur())
			return true;

		return false;
	}

	/**
	 * Permet de savoir de l'exterieur si la carte est jouable sur le tas actuel
	 * 
	 * @return vrai si la carte est jouable
	 */
	public boolean jouable() {
		Carte derniere_carte_jouee = Tas.getInstance().getDerniereCarte();
		if (derniere_carte_jouee == null) {
			return true;
		}
		return derniere_carte_jouee.jouableParDessus(this);
	}

	/**
	 * Permet de jouer la carte (inutile pour une carte normale mais permet
	 * d'appliquer les effets des cartes speciales)
	 */
	public void jouer(Partie partie, Joueur joueur) {
		this.poser();
	}

	/**
	 * Permet d'ajouter la carte au tas
	 */
	protected void poser() {
		if (this.jouable()) {
			Tas.getInstance().ajouterCarte(this);
		}
	}

	/*
	 * GETTERS
	 */
	/**
	 * Permet de récuperer la valeur de la carte
	 * 
	 * @return La valeur de la carte qui est un entier
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * Permet de récuperer le nom de la carte
	 * 
	 * @return Le nom de la carte sous forme de chaine
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Permet de récuperer l'enseigne de la carte
	 * 
	 * @return L'enseigne de la carte
	 */
	public Enseigne getEnseigne() {
		return this.enseigne;
	}

}
