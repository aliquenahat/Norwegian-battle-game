package fr.utt.lo02.paynatquenahat.cartes;

/**
 * Classe héritant de Carte qui représente le 2
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Deux extends Carte {

	/**
	 * Constructeur de la classe
	 * 
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public Deux(String enseigne) {
		super(2, "2", enseigne);
	}

	/*
	 * JOUER LA CARTE
	 */

	/**
	 * La carte Deux peut être jouée tout le temps
	 * 
	 * @return vrai
	 **/
	public boolean jouable() {
		return true;
	}

}
