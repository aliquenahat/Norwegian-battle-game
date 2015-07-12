package fr.utt.lo02.paynatquenahat.joueurs;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.cartes.*;
import fr.utt.lo02.paynatquenahat.paquet.Pioche;
import fr.utt.lo02.paynatquenahat.partie.*;

/**
 * Classe de gestion des joueurs
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public abstract class Joueur {

	// attributs
	private int numero;
	private NomJoueur nom;
	private TypeJoueur type;
	protected int nb_cartes = 0;
	protected ArrayList<Carte> cartes_cachees = new ArrayList<Carte>();
	protected ArrayList<Carte> cartes_visibles = new ArrayList<Carte>();
	protected ArrayList<Carte> cartes_main = new ArrayList<Carte>();
	protected Partie partie;

	/**
	 * Constructeur du Joueur : définit les attributs
	 * 
	 * @param game
	 *            instance de Partie
	 * @param num
	 *            numéro du joueur dans la partie
	 * @param name
	 *            nom du joueur (si joueur physique le nom est "Vous", sinon
	 *            c'est un nom aléatoire pris dans la liste NomJoueur)
	 * @param typ
	 *            type du joueur : physique ou virtuel (TypeJoueur)
	 */
	public Joueur(Partie game, int num, NomJoueur name, TypeJoueur typ) {
		this.setPartie(game);
		this.setNumero(num);
		this.setNom(name);
		this.setTypeJoueur(typ);
	}

	/**
	 * Fais jouer le joueur (méthode implémentée dans les classes filles
	 * JoueurPhysique et JoueurVirtuel)
	 */
	public abstract void jouerTour();

	/**
	 * Permet de récupérer les cartes de la main qui ont un certain nom
	 * 
	 * @param nom_carte
	 *            nom de la carte à chercher
	 * @return une ArrayList des cartes présentes avec ce nom
	 */
	protected ArrayList<Carte> cartesDeCeNomDansLaMain(ArrayList<Carte> array,
			String nom_carte) {
		ArrayList<Carte> retour = new ArrayList<Carte>();
		Iterator<Carte> it = this.cartes_main.iterator();
		Carte carte;
		while (it.hasNext()) {
			carte = it.next();
			if (carte.getNom().toLowerCase().equals(nom_carte)) {
				retour.add(carte);
			}
		}
		return retour;
	}

	/**
	 * Jouer une carte
	 * 
	 * @param carte
	 *            la carte à jouer (poser sur le tas)
	 */
	public void jouerCarte(Carte carte) {
		// on pose la carte
		carte.jouer(this.partie, this);
		// on retire la carte de la main du joueur
		Iterator<Carte> it = this.cartes_main.iterator();
		int i = 0;
		while (it.hasNext()) {
			if (it.next().equals(carte)) {
				break;
			}
			i++;
		}
		this.cartes_main.remove(i);
		// on met a jour le nombre de cartes
		this.nb_cartes--;
	}

	/**
	 * Piocher une carte dans la Pioche
	 */
	public void piocher() {
		Pioche pioche = Pioche.getInstance();
		if (pioche.getNbCartes() > 0) {
			this.donnerCarteMain(pioche.piocherCarte());
		}
	}

	/**
	 * Ramasser les cartes face visible
	 */
	public void prendreCartesVisibles() {
		Carte temp;
		while (this.cartes_visibles.size() > 0) {
			temp = this.retirerCarteVisible();
			this.donnerCarteMain(temp);
		}
		Ecran.TOUR_joueurRamasseCartesVisibles(this);
	}

	/**
	 * Ramasser une carte face cachée
	 */
	public void prendreCarteCachee() {
		Carte temp = this.retirerCarteCachee();
		this.donnerCarteMain(temp);
		Ecran.TOUR_joueurRamasseCarteCachee(this);
	}

	/**
	 * Permet au joueur d'echanger ses cartes visibles avec les cartes qu'il a
	 * dans la main (implémentée par les classes filles JoueurVirtuel et
	 * JoueurPhysique)
	 */
	public abstract void echangerCartesVisiblesMain();

	/**
	 * Permet au joueur d'envoyer le tas a l'adversaire de son choix
	 * (implémentée par les classes filles JoueurVirtuel et JoueurPhysique)
	 */
	public abstract void envoyerTasAAdversaire();

	/**
	 * Ajoute une carte face cachée au joueur (pendant distribution)
	 * 
	 * @param carte
	 *            la carte a ajouter
	 */
	public void donnerCarteCachee(Carte carte) {
		this.cartes_cachees.add(carte);
		this.nb_cartes++;
	}

	/**
	 * Supprime et renvoie une carte face cachée.
	 * 
	 * @return la carte retirée du "tas cartes face cachée"
	 */
	public Carte retirerCarteCachee() {
		Carte temp = this.cartes_cachees.get(0);
		this.cartes_cachees.remove(0);
		this.nb_cartes--;
		return temp;
	}

	/**
	 * Ajoute une carte face visible au joueur
	 * 
	 * @param carte
	 *            la carte à ajouter
	 */
	public void donnerCarteVisible(Carte carte) {
		this.cartes_visibles.add(carte);
		this.nb_cartes++;
	}

	/**
	 * Supprime et renvoie une carte face visible
	 * 
	 * @return la carte retirée du "tas cartes face visible"
	 */
	public Carte retirerCarteVisible() {
		Carte temp = this.cartes_visibles.get(0);
		this.cartes_visibles.remove(0);
		this.nb_cartes--;
		return temp;
	}

	/**
	 * Ajoute une carte à la main du joueur
	 * 
	 * @param carte
	 */
	public void donnerCarteMain(Carte carte) {
		this.cartes_main.add(carte);
		this.nb_cartes++;
	}

	/**
	 * Trie les cartes par ordre croissant de valeur
	 */
	public void trierCartesMain() {
		this.triParValeurCartesMain(0, this.cartes_main.size() - 1);
	}

	/**
	 * Permet d'échanger deux index de cartes dans la liste (pour le tri)
	 * 
	 * @param a
	 *            index de la 1ere carte à echanger
	 * @param b
	 *            index de la 2e carte à échanger
	 */
	private void permuteCartesMain(int a, int b) {
		Carte carte_c = this.cartes_main.get(a);
		this.cartes_main.set(a, this.cartes_main.get(b));
		this.cartes_main.set(b, carte_c);
	}

	/**
	 * Algorithme récursif de type "QuickSort" pour trier les cartes du joueur
	 * 
	 * @param deb
	 *            début du tri sur la liste (au début 0 car on trie dès le début
	 *            de la liste)
	 * @param fin
	 *            fin du tri sur la liste (au début arraylist.size()-1 pour
	 *            trier jusqu'au bout du tableau)
	 */
	private void triParValeurCartesMain(int deb, int fin) {
		int gauche = deb - 1;
		int droite = fin + 1;
		int pivot = this.cartes_main.get(deb).getValeur();
		// tableau de longueur nulle => on est arrive a la fin du tri
		// => on arrete
		if (deb >= fin) {
			return;
		}
		// On parcours la liste avec deux "curseurs" (gauche & droite) pour
		// trouver un element mal place.
		// On va permuter tous les elements en fonction du pivot :
		// ceux qui sont inferieurs au pivot vont aller a gauche ;
		// ceux qui sont superieurs au pivot vont aller a droite.
		while (true) {
			do
				droite--;
			while (this.cartes_main.get(droite).getValeur() > pivot);
			do
				gauche++;
			while (this.cartes_main.get(gauche).getValeur() < pivot);
			if (gauche < droite) { // deux elements sont mal places
				this.permuteCartesMain(gauche, droite);
			} else { // les deux curseurs se croisent, donc tous les elements on
						// ete tries par rapport au pivot
				break;
			}
		}
		// Tous les elements de la liste sont ranges d'un cote ou de l'autre du
		// pivot.
		// On appelle par recurrence la meme methode pour ranger la partie
		// gauche et la partie droite.
		this.triParValeurCartesMain(deb, droite);
		this.triParValeurCartesMain(droite + 1, fin);
	}

	/**
	 * Permute deux cartes entre le "tas cartes face visible" et la mail
	 * 
	 * @param A
	 *            liste A (par exemple this.cartes_visibles)
	 * @param index_A
	 *            index de la 1ere carte à échanger (dans la liste A)
	 * @param B
	 *            liste B (par exemple this.cartes_main)
	 * @param index_B
	 *            index de la 2e carte à échanger (dans la liste B)
	 */
	protected void permuteCartesVisiblesMain(ArrayList<Carte> A, int index_A,
			ArrayList<Carte> B, int index_B) {
		Carte c = A.get(index_A);
		A.set(index_A, B.get(index_B));
		B.set(index_B, c);
	}

	/**
	 * Définit l'instance de Partie
	 * 
	 * @param game
	 *            instance de Partie
	 */
	public void setPartie(Partie game) {
		this.partie = game;
	}

	/**
	 * Définit le numéro du joueur
	 * 
	 * @param num
	 *            numéro du joueur dans la partie
	 */
	public void setNumero(int num) {
		this.numero = num;
	}

	/**
	 * Définit le nom du joueur
	 * 
	 * @param name
	 *            nom du joueur dans la partie
	 */
	public void setNom(NomJoueur name) {
		this.nom = name;
	}

	/**
	 * Définit le type de joueur (physique ou virtuel)
	 * 
	 * @param typ
	 *            type du joueur (TypeJoueur)
	 */
	public void setTypeJoueur(TypeJoueur typ) {
		this.type = typ;
	}

	/*
	 * GETTERS
	 */
	/**
	 * recupere la liste des autres joueurs afin d'adapter sa strategie en
	 * fonction des cartes des autres (ex: savoir a qui on envoie le tas quand
	 * on pose un As)
	 * 
	 * @return une ArrayList des autres joueurs de la partie (tout le monde sauf
	 *         le joueur qui demande la liste)
	 */
	public ArrayList<Joueur> getAutresJoueurs() {
		ArrayList<Joueur> autres_joueurs = new ArrayList<Joueur>();
		Joueur temp;
		Iterator<Joueur> it = this.partie.getListeJoueurs().iterator();
		// on parcourt tous les joueurs et on retire juste le joueur actuel
		while (it.hasNext()) {
			temp = it.next();
			if (!temp.equals(this)) {
				autres_joueurs.add(temp);
			}
		}
		return autres_joueurs;
	}

	/**
	 * Récupère le numéro du joueur dans la partie
	 * 
	 * @return numéro du joueur dans la partie
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Récupère le nom du joueur
	 * 
	 * @return le nom du joueur
	 */
	public NomJoueur getNom() {
		return this.nom;
	}

	/**
	 * Récupère le type de joueur
	 * 
	 * @return le type du joueur (TypeJoueur)
	 */
	public TypeJoueur getTypeJoueur() {
		return this.type;
	}

	/**
	 * Récupère le nombre total de cartes
	 * 
	 * @return nombre total de cartes du joueur
	 */
	public int getNbCartes() {
		return this.nb_cartes;
	}

	/**
	 * Récupère le nombre de cartes face cachée restantes
	 * 
	 * @return nombre de cartes face cachée
	 */
	public int getNbCartesCachees() {
		return this.cartes_cachees.size();
	}

	/**
	 * Récupère le nombre de cartes face visible restantes
	 * 
	 * @return nombre de cartes face visible
	 */
	public int getNbCartesVisibles() {
		return this.cartes_visibles.size();
	}

	/**
	 * Récupère le nombre de cartes dans la main
	 * 
	 * @return nombre de cartes dans la main
	 */
	public int getNbCartesMain() {
		return this.cartes_main.size();
	}

	/**
	 * Récupère la liste des cartes face cachée
	 * 
	 * @return une ArrayList des cartes face cachée du joueur
	 */
	public ArrayList<Carte> getCartesCachees() {
		return this.cartes_cachees;
	}

	/**
	 * Récupère la liste des cartes face visible
	 * 
	 * @return une ArrayList des cartes face visible
	 */
	public ArrayList<Carte> getCartesVisibles() {
		return this.cartes_visibles;
	}

	/**
	 * Récupère la liste des cartes de la main
	 * 
	 * @return une ArrayList des cartes de la main du joueur
	 */
	public ArrayList<Carte> getCartesMain() {
		return this.cartes_main;
	}

}