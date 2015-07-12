package fr.utt.lo02.paynatquenahat.paquet;

import fr.utt.lo02.paynatquenahat.exception.*;
import fr.utt.lo02.paynatquenahat.cartes.*;
import java.util.ArrayList;

/**
 * Classe de gestion des paquets
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public abstract class Paquet {

	// attributs
	protected int nb_cartes = 0;
	protected ArrayList<Carte> cartes = new ArrayList<Carte>();

	/*
	 * Operations
	 */

	/**
	 * Ajoute une carte au paquet
	 * 
	 * @param carte
	 *            La carte correspondante
	 */
	public void ajouterCarte(Carte carte) {
		this.cartes.add(carte);
		this.nb_cartes++;
	}

	/**
	 * Retire et récupère une carte du paquet à la position indiquée (leve une
	 * exception si aucune carte ne se trouve a la position indiquee)
	 * 
	 * @param index
	 *            L'index de la carte
	 * @return La carte
	 * @throws CarteNotFoundException
	 *             Si la carte n'existe pas
	 */
	public Carte retirerCarte(int index) throws CarteNotFoundException {
		if (index < 0 || index >= this.cartes.size()) {
			throw new CarteNotFoundException();
		}
		Carte c = this.cartes.get(index);
		this.cartes.remove(index);
		this.nb_cartes--;
		return c;
	}

	/*
	 * GETTERS
	 */

	/**
	 * Permet de récuperer les cartes et le nombre de cartes du paquet
	 * 
	 * @return Le nombre de cartes
	 */
	public int getNbCartes() {
		return this.nb_cartes;
	}

	/**
	 * Permet de récuperer la liste des cartes
	 * 
	 * @return La liste des cartes
	 */
	public ArrayList<Carte> getCartes() {
		return this.cartes;
	}
}