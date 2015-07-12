package fr.utt.lo02.paynatquenahat.affichage;

import java.util.ArrayList;

import fr.utt.lo02.paynatquenahat.configuration.Config;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.cartes.*;

public class Ecran {

	/**
	 * Affiche le début du programme
	 */
	public static void demarrage() {
		Config.getInstanceModeAffichage().demarrage();
	}

	/**
	 * Affiche les infos sur le nombre de joueurs virtuels ainsi que leurs noms
	 * avant que la partie ne commence
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public static void infosJoueursAvantPartie(ArrayList<Joueur> joueurs) {
		Config.getInstanceModeAffichage().infosJoueursAvantPartie(joueurs);
	}

	/**
	 * Affiche les infos pour le début de la partie et indique qui distribue et
	 * qui commence à jouer
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public static void infosJoueursDebutPartie(ArrayList<Joueur> joueurs) {
		Config.getInstanceModeAffichage().infosJoueursDebutPartie(joueurs);
	}

	/**
	 * Indique le nom du joueur qui a choisi de ne pas echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public static void infosEchangerCartesDebut_non(Joueur j) {
		Config.getInstanceModeAffichage().infosEchangerCartesDebut_non(j);
	}

	/**
	 * Affiche les infos sur les cartes du joueur physique pour lui proposer
	 * d'echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public static void infosCartesAEchangerDebutPartie(Joueur j) {
		Config.getInstanceModeAffichage().infosCartesAEchangerDebutPartie(j);
	}

	/**
	 * Affiche les infos sur le tour qui vient de commencer
	 * 
	 * @param joueurs
	 *            Liste des joueurs
	 * @param tour_actuel
	 *            Le numéro du tour actuel
	 * @param titre_tour
	 *            Indique si c'est un nouveau tour
	 */
	public static void infosTourTable(ArrayList<Joueur> joueurs, int tour,
			boolean titre_tour) {
		Config.getInstanceModeAffichage().infosTourTable(joueurs, tour,
				titre_tour);
	}

	/**
	 * Affiche la liste des autres joueurs lorsqu'on doit choisir le joueur qui
	 * a envoyé le tas
	 * 
	 * @param autres
	 *            La liste des autres joueurs
	 */
	public static void infosAutresJoueursEnvoyerTas(ArrayList<Joueur> autres) {
		Config.getInstanceModeAffichage().infosAutresJoueursEnvoyerTas(autres);
	}

	/**
	 * Affiche quand un joueur passe son tour
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public static void TOUR_joueurPasseSonTour(Joueur j) {
		Config.getInstanceModeAffichage().TOUR_joueurPasseSonTour(j);
	}

	/**
	 * Affichage du joueur qui est entrain de joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public static void TOUR_textJoueurQuiJoue(Joueur j) {
		Config.getInstanceModeAffichage().TOUR_textJoueurQuiJoue(j);
	}

	/**
	 * Affiche les cartes que le joueur physique peut jouer
	 * 
	 * @param cartes
	 *            Liste des cartes
	 */
	public static void TOUR_infosCartesPouvantEtreJouees(ArrayList<Carte> cartes) {
		Config.getInstanceModeAffichage().TOUR_infosCartesPouvantEtreJouees(
				cartes);
	}

	/**
	 * Affiche la carte et le nombre de cartes posées par le joueur
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 * @param carte
	 *            La carte concernée
	 * @param nb_a_poser
	 *            Le nombre de carte a posé
	 */
	public static void TOUR_joueurPoseCartes(Joueur j, Carte c, int nb) {
		Config.getInstanceModeAffichage().TOUR_joueurPoseCartes(j, c, nb);
	}

	/**
	 * Affiche le nombre de cartes piochées par le joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 * @param nb_piochages
	 *            Le nombre de cartes piochées
	 */
	public static void TOUR_joueurPiocheCartes(Joueur j, int nb) {
		Config.getInstanceModeAffichage().TOUR_joueurPiocheCartes(j, nb);
	}

	/**
	 * Affiche quand un joueur ramasse le tas et le nombre de cartes qu'il
	 * ramasse
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 * @param nb_cartes_ramssees
	 *            Le nombre de cartes ramassées
	 */
	public static void TOUR_joueurRamasseLeTas(Joueur j, int nb) {
		Config.getInstanceModeAffichage().TOUR_joueurRamasseLeTas(j, nb);
	}

	/**
	 * Affiche quand un joueur ramasse ses 3 cartes visibles devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */
	public static void TOUR_joueurRamasseCartesVisibles(Joueur j) {
		Config.getInstanceModeAffichage().TOUR_joueurRamasseCartesVisibles(j);
	}

	/**
	 * Affiche quand un joueur ramasse une carte face cachee devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */
	public static void TOUR_joueurRamasseCarteCachee(Joueur j) {
		Config.getInstanceModeAffichage().TOUR_joueurRamasseCarteCachee(j);
	}

	/**
	 * Affiche quand un joueur envoie le tas à un autre joueur
	 * 
	 * @param envoyeur
	 *            Le joueur qui envoie
	 * @param receveur
	 *            Le joueur qui recoit
	 * @param nb_cartes
	 *            Le nombre de cartes envoyées
	 */
	public static void TOUR_envoyerTas(Joueur j1, Joueur j2, int nb) {
		Config.getInstanceModeAffichage().TOUR_envoyerTas(j1, j2, nb);
	}

	/**
	 * Affiche un message quand un 7 est posé
	 */
	public static void TOUR_7pose() {
		Config.getInstanceModeAffichage().TOUR_7pose();
	}

	/**
	 * Affiche un message quand un 10 est posé
	 */
	public static void TOUR_10pose() {
		Config.getInstanceModeAffichage().TOUR_10pose();
	}

	/**
	 * Affiche les infos a la fin de la partie
	 * 
	 * @param joueur
	 *            Le joueur vainqueur
	 */
	public static void afficheFinDePartie(Joueur vainqueur) {
		Config.getInstanceModeAffichage().afficheFinDePartie(vainqueur);
	}

	/*
	 * Interactions
	 */
	/**
	 * Demande a l'utilisateur de saisir quelque chose au clavier
	 * 
	 * @param msg
	 *            Le message qui attends une réponse
	 * @return Retourne la saisie correspondante
	 */
	public static String prompt(String msg) {
		return Config.getInstanceModeAffichage().prompt(msg);
	}

	/*
	 * Affichage de texte
	 */

	/**
	 * Affichage d'une action (ex: distribuer les cartes)
	 * 
	 * @param msg
	 *            L'action concernée
	 */
	public static void actionDebut(String msg) {
		Config.getInstanceModeAffichage().actionDebut(msg);
	}

	/**
	 * Affiche un morceau de chargement (un "." a chaque avancement du
	 * chargement)
	 */
	public static void chargementSupp() {
		Config.getInstanceModeAffichage().chargementSupp();
	}

	/**
	 * Termine l'affichage d'une action
	 */
	public static void actionFin() {
		Config.getInstanceModeAffichage().actionFin();
	}

	/**
	 * Affiche un message "appuyez sur entrer pour continuer"
	 */
	public static void appuyezEntrer() {
		Config.getInstanceModeAffichage().appuyezEntrer();
	}

	/*
	 * Erreurs
	 */

	/**
	 * Affiche les details d'un bug dans le programme et termine celui-ci
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public static void bug(String msg) {
		Config.getInstanceModeAffichage().bug(msg);
	}

	/**
	 * Affiche une erreur venue de l'utilisateur ( d'ou la difference avec
	 * bug())
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public static void error(String msg) {
		Config.getInstanceModeAffichage().error(msg);
	}

	/*
	 * Special
	 */

	/**
	 * Arrêt du programme pendant un temps
	 */
	public static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Mets le programme en pause, attend que l'utilisateur appuye sur [entrer]
	 */
	public static void pause() {
		Config.getInstanceModeAffichage().pause();
	}

}