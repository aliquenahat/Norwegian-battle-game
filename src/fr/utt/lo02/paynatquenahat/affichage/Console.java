package fr.utt.lo02.paynatquenahat.affichage;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.lo02.paynatquenahat.cartes.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.paquet.*;

public class Console implements Affichage {

	// attributs
	private static Affichage _instance = null;
	private Scanner keyboard = new Scanner(System.in);

	/*
	 * Singleton
	 */
	/**
	 * Il s'agit d'un singleton
	 */
	private Console() {
	}

	/**
	 * 
	 * @return l'instance d'Affichage
	 */
	public static Affichage getInstance() {
		if (_instance == null) {
			_instance = new Console();
		}
		return _instance;
	}

	/**
	 * Affiche le début du programme
	 */
	public void demarrage() {
		this.textln("################################");
		this.textln("##                            ##");
		this.textln("##    BATAILLE NORVEGIENNE    ##");
		this.textln("###       Projet LO02        ###");
		this.textln("##                            ##");
		this.textln("################################\n");

		this.textln("Vous etes sur le point de demarrer une partie de Bataille Norvegienne.\n");
	}

	/**
	 * Affiche les infos sur le nombre de joueurs virtuels ainsi que leurs noms
	 * avant que la partie ne commence
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public void infosJoueursAvantPartie(ArrayList<Joueur> joueurs) {
		Iterator<Joueur> it = joueurs.iterator();
		Joueur j;
		this.text("\nVous allez jouer la partie contre " + (joueurs.size() - 1)
				+ " joueur(s) virtuel(s) : ");

		boolean first = true;
		while (it.hasNext()) {
			j = it.next();
			if (j.getTypeJoueur() == TypeJoueur.virtuel) {
				if (!first) {
					this.text(", ");
				}
				this.text("" + j.getNom());
				first = false;
			}
		}
		this.textln(".\n");
	}

	/**
	 * Affiche les infos pour le début de la partie et indique qui distribue et
	 * qui commence à jouer
	 * 
	 * @param joueurs
	 *            Liste de joueurs
	 */
	public void infosJoueursDebutPartie(ArrayList<Joueur> joueurs) {
		// infos joueur qui a distribue et premier a jouer
		String result = joueurs.get(joueurs.size() - 1).getNom() + " ";
		result = (joueurs.get(joueurs.size() - 1).getTypeJoueur() == TypeJoueur.physique) ? result
				+ "venez"
				: result + "vient";
		this.textln(result + " de distribuer les cartes.");
		result = joueurs.get(0).getNom() + " ";
		result = (joueurs.get(0).getTypeJoueur() == TypeJoueur.physique) ? result
				+ "commencerez"
				: result + "commencera";
		this.textln(result + " a jouer.\n");
		// infos cartes distribuees aux joueurs
		this.infosTourTable(joueurs, 0, false);
		// mise en pause pour laisser a l'utilisateur le temps de lire
		this.appuyezEntrer();
		// on affiche le message d'apres, pour l'echange des cartes
		this.msgPrincipal("...echanges de cartes visibles/main...");
	}

	/**
	 * Indique le nom du joueur qui a choisi de ne pas echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public void infosEchangerCartesDebut_non(Joueur j) {
		this.textln(j.getNom() + " a choisi de ne pas echanger ses cartes.\n");
	}

	/**
	 * Affiche les infos sur les cartes du joueur physique pour lui proposer
	 * d'echanger ses cartes
	 * 
	 * @param joueur
	 *            Le joueur qui est concerné
	 */
	public void infosCartesAEchangerDebutPartie(Joueur joueur) {
		ArrayList<Carte> visibles = joueur.getCartesVisibles();
		ArrayList<Carte> main = joueur.getCartesMain();
		this.textln("    _____________________________________");
		this.textln("   /                                     \\");
		this.textln("   | Cartes Visibles:\t     Main:");
		for (int i = 0; i < 3; i++) {
			this.text("   | " + (i + 1) + ") ");
			this.afficheCarte(visibles.get(i).getNom());
			this.text("\t\t     " + (i + 4) + ") ");
			this.afficheCarte(main.get(i).getNom());
			this.text("\n");
			if (i == 2)
				this.textln("   \\_____________________________________/");
		}
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
	public void infosTourTable(ArrayList<Joueur> joueurs, int tour_actuel,
			boolean titre_tour) {
		// message principal
		if (titre_tour) {
			this.msgPrincipal("........Tour _" + tour_actuel + "_........");
		}

		this.textln("#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");
		// nb de cartes sur le tas et dans la pioche
		Tas tas = Tas.getInstance();
		this.text("# TAS : " + tas.getNbCartes() + " carte(s)");
		Pioche pioche = Pioche.getInstance();
		this.text("\tPIOCHE : " + pioche.getNbCartes() + " carte(s)\n#\n");

		// infos sur les joueurs
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			this.infosJoueur(it.next());
		}
		this.textln("#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");
	}

	/**
	 * Calcul du nombre d'espace à mettre pour l'alignement des textes
	 * 
	 * @param longueur
	 *            Longueur de la chaîne
	 * @param texte_a_retirer
	 *            Le texte à enlever
	 * @param supp
	 *            sous forme d'entier
	 */
	private void blancs(int longueur, String texte_a_retirer, int supp) {
		int blancs = longueur - texte_a_retirer.length() - supp;
		for (int i = 0; i < blancs; i++) {
			this.text(" ");
		}
	}

	/**
	 * Affiche les infos sur le joueur au début du tour
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	private void infosJoueur(Joueur j) {
		Iterator<Carte> it;
		Carte temp;
		String blcs = "";
		int blcs_supp = 0;

		// affichage numero & nom
		this.text("# >");
		this.blancs(19, j.getNom() + "", 0);
		this.text(j.getNom() + ":    ");

		// affichage carte face cachee
		it = j.getCartesCachees().iterator();
		while (it.hasNext()) {
			this.afficheCarte(".");
			it.next();
			blcs += ".";
			blcs_supp += 2;
		}
		this.blancs(15, blcs, blcs_supp);

		blcs = "";
		blcs_supp = 0;
		// affichage carte face visible
		it = j.getCartesVisibles().iterator();
		while (it.hasNext()) {
			temp = it.next();
			this.afficheCarte(temp.getNom());
			blcs += temp.getNom();
			blcs_supp += 2;
		}
		this.blancs(24, blcs, blcs_supp);

		// affichage nombre de carte en main
		this.text("MAIN= ");
		it = j.getCartesMain().iterator();
		if (j.getTypeJoueur() == TypeJoueur.physique) {
			// si c'est le joueur physique alors on affiche ses cartes
			while (it.hasNext()) {
				this.afficheCarte(it.next().getNom());
			}
		} else {
			// si c'est un joueur virtuel alors on affiche juste le nombre de
			// cartes dans la main
			while (it.hasNext()) {
				this.afficheCarte(".");
				it.next();
			}
		}
		this.text("\n");
	}

	// affiche une carte a l'ecran
	private void afficheCarte(String text) {
		this.text("[" + text + "]");
	}

	/**
	 * Affiche la liste des autres joueurs lorsqu'on doit choisir le joueur qui
	 * a envoyé le tas
	 * 
	 * @param autres
	 *            La liste des autres joueurs
	 */
	public void infosAutresJoueursEnvoyerTas(ArrayList<Joueur> autres) {
		Iterator<Joueur> it = autres.iterator();
		Joueur temp;
		int index = 1;

		this.text("\tVeuillez choisir le joueur a qui envoyer le Tas : (son numero)\n\t");
		while (it.hasNext()) {
			temp = it.next();
			this.text("   (" + index + ")" + temp.getNom());
			index++;
		}
		this.text("\n");
	}

	/**
	 * Affiche quand un joueur passe son tour
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public void TOUR_joueurPasseSonTour(Joueur joueur) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "passez votre"
				: "passe son";

		this.textln("\n" + joueur.getNom() + " " + lang + " tour.");
	}

	/**
	 * Affichage du joueur qui est entrain de joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public void TOUR_textJoueurQuiJoue(Joueur joueur) {
		this.textln("\nC'est a " + joueur.getNom() + " de jouer.");
		if (joueur.getTypeJoueur() == TypeJoueur.physique) {
			ArrayList<Carte> cartes = joueur.getCartesMain();
			Iterator<Carte> it = cartes.iterator();
			this.text("Vos cartes : ");
			while (it.hasNext()) {
				this.afficheCarte(it.next().getNom());
			}
			this.text("\n");
		}
	}

	/**
	 * Affiche les cartes que le joueur physique peut jouer
	 * 
	 * @param cartes
	 *            Liste des cartes
	 */
	public void TOUR_infosCartesPouvantEtreJouees(ArrayList<Carte> cartes) {
		this.text("Cartes jouables : ");
		Iterator<Carte> it = cartes.iterator();
		while (it.hasNext()) {
			this.afficheCarte(it.next().getNom());
		}
		this.text("\n");
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
	public void TOUR_joueurPoseCartes(Joueur joueur, Carte carte, int nb_a_poser) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "posez"
				: "pose";

		this.text(joueur.getNom() + " " + lang + " ###" + nb_a_poser + "x");
		this.afficheCarte(carte.getNom());
		this.text("### \n");
	}

	/**
	 * Affiche le nombre de cartes piochées par le joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 * @param nb_piochages
	 *            Le nombre de cartes piochées
	 */
	public void TOUR_joueurPiocheCartes(Joueur joueur, int nb_piochages) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "piochez"
				: "pioche";

		this.textln(joueur.getNom() + " " + lang + " " + nb_piochages
				+ " carte(s).");
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
	public void TOUR_joueurRamasseLeTas(Joueur joueur, int nb_cartes_ramassees) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "ne pouvez pas jouer, et ramassez le Tas"
				: "ne peut pas jouer, et ramasse le Tas";

		this.textln(joueur.getNom() + " " + lang + " (" + nb_cartes_ramassees
				+ " carte(s)).");
	}

	/**
	 * Affiche quand un joueur ramasse ses 3 cartes visibles devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */
	public void TOUR_joueurRamasseCartesVisibles(Joueur joueur) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "prenez vos"
				: "prend ses";

		this.textln(joueur.getNom() + " " + lang
				+ " trois cartes face visible.");
	}

	/**
	 * Affiche quand un joueur ramasse une carte face cachee devant lui
	 * 
	 * @param param
	 *            Le joueur concerné
	 */
	public void TOUR_joueurRamasseCarteCachee(Joueur joueur) {
		String lang;
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "prenez une de vos"
				: "prend une de ses";

		this.textln(joueur.getNom() + " " + lang + " cartes face cachee.");
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
	public void TOUR_envoyerTas(Joueur envoyeur, Joueur receveur, int nb_cartes) {
		String lang;
		lang = (envoyeur.getTypeJoueur() == TypeJoueur.physique) ? "envoyez"
				: "envoie";

		this.textln(envoyeur.getNom() + " " + lang + " le Tas a "
				+ receveur.getNom() + " (" + nb_cartes + " carte(s))");
	}

	/**
	 * Affiche un message quand un 7 est posé
	 */
	public void TOUR_7pose() {
		this.textln("La prochaine carte posee doit avoir une valeur inferieure a 7.");
	}

	/**
	 * Affiche un message quand un 10 est posé
	 */
	public void TOUR_10pose() {
		this.textln("Les cartes du Tas sont retirees du jeu.");
	}

	/**
	 * Affiche les infos a la fin de la partie
	 * 
	 * @param joueur
	 *            Le joueur vainqueur
	 */
	public void afficheFinDePartie(Joueur vainqueur) {
		this.textln("\n\n################################");
		this.textln("##                            ##");
		this.textln("##   LA PARTIE EST TERMINEE   ##");
		this.textln("##      => Le Danish est " + vainqueur.getNom()
				+ " car il a gagne la partie.");
		this.textln("################################\n");
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
	public String prompt(String msg) {
		this.text(msg);
		String saisie = this.keyboard.nextLine();
		return saisie;
	}

	/*
	 * Affichage de texte PRIVATE
	 */
	/**
	 * Affiche une ligne de texte
	 * 
	 * @param msg
	 *            Le message à afficher
	 */
	public void textln(String msg) {
		System.out.println(msg);
	}

	/**
	 * Affiche du texte sans retour a la ligne
	 * 
	 * @param msg
	 *            Le message à afficher
	 */
	public void text(String msg) {
		System.out.printf(msg);
	}

	/**
	 * Affichage d'une action (ex: distribuer les cartes)
	 * 
	 * @param msg
	 *            L'action concernée
	 */
	public void actionDebut(String msg) {
		System.out.println("\t---------------------");
		System.out.printf("\t@@@@- " + msg);
	}

	/**
	 * Affiche un morceau de chargement (un "." a chaque avancement du
	 * chargement)
	 */
	public void chargementSupp() {
		System.out.printf(".");
	}

	/**
	 * Termine l'affichage d'une action
	 */
	public void actionFin() {
		System.out.printf(" OK\n\n");
	}

	/**
	 * message principal (ex: debut d'un nouveau tour)
	 * 
	 * @param msg
	 *            Le message à afficher
	 */
	public void msgPrincipal(String msg) {
		int taille = msg.length(), i = 0;
		String hole = "";

		while (i != taille) {
			hole = hole + "~";
			i++;
		}

		System.out.println("\n   /" + hole + "\\");
		System.out.println("~~~ " + msg + " ~~~");
		System.out.println("   \\" + hole + "/\n");
	}

	/**
	 * Affiche un message "appuyez sur entrer pour continuer"
	 */
	public void appuyezEntrer() {
		this.text("\n\t..appuyez sur {Entrer} pour continuer..");
		this.pause();
		this.text("\n");
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
	public void bug(String msg) {
		System.out.println("@@@@@@@@-----@@@@@@@@");
		this.error("/ERREUR INTERNE\\ " + msg);
		System.out.println("@@@@@@@@-----@@@@@@@@");
		System.exit(0);
	}

	/**
	 * Affiche une erreur venue de l'utilisateur ( d'ou la difference avec
	 * bug())
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public void error(String msg) {
		System.out.println("/!\\ ERREUR - " + msg + "\n");
	}

	/*
	 * Special
	 */

	/**
	 * Mets le programme en pause, attend que l'utilisateur appuye sur [entrer]
	 */
	public void pause() {
		this.keyboard.nextLine();
	}

}