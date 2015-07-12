package fr.utt.lo02.paynatquenahat.paquet;

import fr.utt.lo02.paynatquenahat.cartes.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe de gestion d'un jeu de cartes
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class JeuCartes {

	// attributs
	private int nb_cartes = 0;
	private ArrayList<Carte> cartes = new ArrayList<Carte>();

	/*
	 * CONSTRUCTEUR
	 */

	/**
	 * On crée le paquet complet des la creation de l'objet
	 * 
	 * @param nb_joueurs
	 *            Le nombre de joueurs
	 */
	public JeuCartes(int nb_joueurs) {
		// On cree le nombre necessaire de paquets de cartes
		// On a besoin de 9 cartes par joueur.
		int nb_cartes_minimum = nb_joueurs * 9;
		while (this.nb_cartes < nb_cartes_minimum) {
			this.creerPaquet52();
		}
	}

	/*
	 * OPERATIONS SUR LES CARTES
	 */

	/**
	 * Permet de mélanger les cartes du paquet (pour les distibuer ensuite)
	 */
	public void melangerCartes() {
		Collections.shuffle(this.cartes);
	}

	/*
	 * CREATION DU PAQUET
	 */

	/**
	 * Créé un paquet entier de 52 cartes (le stocke dans la liste)
	 */
	private void creerPaquet52() {
		this.creerCouleurComplete("Carreau");
		this.creerCouleurComplete("Coeur");
		this.creerCouleurComplete("Pique");
		this.creerCouleurComplete("Trefle");
	}

	/**
	 * Créé une couleur complète de cartes (la stocke dans la iste)
	 * 
	 * @param enseigne
	 *            L'enseigne correspondant
	 */
	private void creerCouleurComplete(String enseigne) {
		// On ajoute le DEUX
		this.cartes.add(new Deux(enseigne));
		this.nb_cartes++;
		this.ajouterCarteNormale(3, "3", enseigne);
		this.ajouterCarteNormale(4, "4", enseigne);
		this.ajouterCarteNormale(5, "5", enseigne);
		this.ajouterCarteNormale(6, "6", enseigne);
		// On ajoute le SEPT
		this.cartes.add(new Sept(enseigne));
		this.nb_cartes++;
		// On ajoute le HUIT
		this.cartes.add(new Huit(enseigne));
		this.nb_cartes++;
		this.ajouterCarteNormale(9, "9", enseigne);
		// On ajoute le DIX
		this.cartes.add(new Dix(enseigne));
		this.nb_cartes++;
		this.ajouterCarteNormale(11, "Valet", enseigne);
		this.ajouterCarteNormale(12, "Dame", enseigne);
		this.ajouterCarteNormale(13, "Roi", enseigne);
		// On ajoute l'AS
		this.cartes.add(new As(enseigne));
		this.nb_cartes++;
	}

	/**
	 * Créé une carte suivant les informations donnees (la stocke dans la liste)
	 * 
	 * @param valeur
	 *            Valeur de la carte
	 * @param nom
	 *            Nom de la carte
	 * @param enseigne
	 *            Enseigne de la carte
	 */
	private void ajouterCarteNormale(int valeur, String nom, String enseigne) {
		Carte newcarte = new Carte(valeur, nom, enseigne);
		this.cartes.add(newcarte);
		this.nb_cartes++;
	}

	/*
	 * GETTERS
	 */

	/**
	 * Permet de récuperer toutes les cartes du paquet complet
	 * 
	 * @return la liste des cartes
	 */
	public ArrayList<Carte> getCartes() {
		return this.cartes;
	}
}
