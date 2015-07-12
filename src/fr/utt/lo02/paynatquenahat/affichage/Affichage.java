package fr.utt.lo02.paynatquenahat.affichage;

import java.util.ArrayList;

import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.cartes.*;

/**
 * Interface d'affichage qui sera implémenté la classe Console et Fenetre
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public interface Affichage {

	/**
	 * Affiche le début du programme
	 */
	public void demarrage();

	/**
	 * Affiche les infos sur le nombre de joueurs virtuels ainsi que leurs noms
	 * avant que la partie ne commence
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public void infosJoueursAvantPartie(ArrayList<Joueur> joueurs);

	/**
	 * Affiche les infos pour le début de la partie et indique qui distribue et
	 * qui commence à jouer
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public void infosJoueursDebutPartie(ArrayList<Joueur> joueurs);

	/**
	 * Indique le nom du joueur qui a choisi de ne pas echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public void infosEchangerCartesDebut_non(Joueur j);

	/**
	 * Affiche les infos sur les cartes du joueur physique pour lui proposer
	 * d'echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public void infosCartesAEchangerDebutPartie(Joueur j);

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
	public void infosTourTable(ArrayList<Joueur> joueurs, int tour,
			boolean titre_tour);

	/**
	 * Affiche la liste des autres joueurs lorsqu'on doit choisir le joueur qui
	 * a envoyé le tas
	 * 
	 * @param autres
	 *            La liste des autres joueurs
	 */
	public void infosAutresJoueursEnvoyerTas(ArrayList<Joueur> autres);

	/**
	 * Affiche quand un joueur passe son tour
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public void TOUR_joueurPasseSonTour(Joueur joueur);

	/**
	 * Affichage du joueur qui est entrain de joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public void TOUR_textJoueurQuiJoue(Joueur joueur);

	/**
	 * Affiche les cartes que le joueur physique peut jouer
	 * 
	 * @param cartes
	 *            Liste des cartes
	 */
	public void TOUR_infosCartesPouvantEtreJouees(ArrayList<Carte> cartes);

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
	public void TOUR_joueurPoseCartes(Joueur joueur, Carte carte, int nb_a_poser);

	/**
	 * Affiche le nombre de cartes piochées par le joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 * @param nb_piochages
	 *            Le nombre de cartes piochées
	 */
	public void TOUR_joueurPiocheCartes(Joueur joueur, int nb_piochages);

	/**
	 * Affiche quand un joueur ramasse le tas et le nombre de cartes qu'il
	 * ramasse
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 * @param nb_cartes_ramssees
	 *            Le nombre de cartes ramassées
	 */
	public void TOUR_joueurRamasseLeTas(Joueur joueur, int nb_cartes_ramassees);

	/**
	 * Affiche quand un joueur ramasse ses 3 cartes visibles devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */
	public void TOUR_joueurRamasseCartesVisibles(Joueur joueur);

	/**
	 * Affiche quand un joueur ramasse une carte face cachee devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */

	public void TOUR_joueurRamasseCarteCachee(Joueur joueur);

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
	public void TOUR_envoyerTas(Joueur envoyeur, Joueur receveur, int nb_cartes);

	/**
	 * Affiche un message quand un 7 est posé
	 */
	public void TOUR_7pose();

	/**
	 * Affiche un message quand un 10 est posé
	 */
	public void TOUR_10pose();

	/**
	 * Affiche les infos a la fin de la partie
	 * 
	 * @param joueur
	 *            Le joueur vainqueur
	 */
	public void afficheFinDePartie(Joueur vainqueur);

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

	public String prompt(String msg);

	/*
	 * Affichage de texte
	 */

	/**
	 * Affichage d'une action (ex: distribuer les cartes)
	 * 
	 * @param msg
	 *            L'action concernée
	 */
	public void actionDebut(String msg);

	/**
	 * Affiche un morceau de chargement (un "." a chaque avancement du
	 * chargement)
	 */
	public void chargementSupp();

	/**
	 * Termine l'affichage d'une action
	 */
	public void actionFin();

	/**
	 * Affiche un message "appuyez sur entrer pour continuer"
	 */
	public void appuyezEntrer();

	/*
	 * Erreurs
	 */

	/**
	 * Affiche les details d'un bug dans le programme et termine celui-ci
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public void bug(String msg);

	/**
	 * Affiche une erreur venue de l'utilisateur ( d'ou la difference avec
	 * bug())
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public void error(String msg);

	/*
	 * Special
	 */

	/**
	 * Mets le programme en pause, attend que l'utilisateur appuye sur [entrer]
	 */
	public void pause();

}