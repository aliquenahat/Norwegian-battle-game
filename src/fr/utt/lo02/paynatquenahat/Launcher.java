package fr.utt.lo02.paynatquenahat;

import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.clavier.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.partie.Partie;
import fr.utt.lo02.paynatquenahat.exception.*;

/**
 * Classe permettant le lancement de l'application
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Launcher {
	/**
	 * Le main
	 * 
	 * @param args
	 */
	// Le programme se lance
	public static void main(String[] args) {
		// Variables
		int nb_joueurs = 0;
		boolean nb_joueurs_OK = false;

		// Début du programme
		Ecran.demarrage();

		/*
		 * Dans un premier temps, on va parametrer la partie (nb de joueurs),
		 * puis la classe Partie va se charger de tout faire ensuite. Choix du
		 * nombre de joueurs
		 */
		do {
			try {
				nb_joueurs = Clavier.getNbJoueurs();
				nb_joueurs_OK = true;
			} catch (InvalidNbJoueursException e) {
				Ecran.error(e.getMessage());
			}
		} while (!nb_joueurs_OK);

		// Creation de la partie et des cartes (dans le constructeur de Partie)
		Partie partie = new Partie(nb_joueurs);

		// Informations sur les autres joueurs
		partie.afficheNomsJoueursVirtuels();
		Ecran.sleep(1000);

		// Lancement de la partie
		partie.launch();

		// On recupere le vainqueur et on affiche le resultat
		Joueur vainqueur = null;
		try {
			vainqueur = partie.getVainqueur();
		} catch (NoWinnerException e) {
			Ecran.bug(e.getMessage());
		}
		Ecran.afficheFinDePartie(vainqueur);
	}

}