package fr.utt.lo02.paynatquenahat.joueurs;

import java.util.ArrayList;

import fr.utt.lo02.paynatquenahat.strategy.*;
import fr.utt.lo02.paynatquenahat.affichage.Ecran;
import fr.utt.lo02.paynatquenahat.cartes.Carte;
import fr.utt.lo02.paynatquenahat.paquet.Pioche;
import fr.utt.lo02.paynatquenahat.paquet.Tas;
import fr.utt.lo02.paynatquenahat.partie.*;

/**
 * Classe de gestion des joueurs virtuel, héritant de la classe Joueur
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class JoueurVirtuel extends Joueur {

	// attributs
	private ArrayList<Carte> cartes_jouables = null;
	private String nom_carte_choisie;
	private int nb_max_cartes_a_poser;
	private int nb_cartes_a_poser;
	private Joueur joueur_a_qui_on_envoie_tas;

	/**
	 * Constructeur spécifique à la classe fille de Joueur (JoueurVirtuel)
	 * 
	 * @param game
	 *            une instance de la Partie
	 * @param num
	 *            le numéro du joueur dans la partie
	 * @param name
	 *            le nom du joueur dans la partie
	 */
	public JoueurVirtuel(Partie game, int num, NomJoueur name) {
		super(game, num, name, TypeJoueur.virtuel);
	}

	/**
	 * Le joueur joue son tour
	 */
	public void jouerTour() {
		// on trie les cartes du joueur
		Ecran.TOUR_textJoueurQuiJoue(this);
		Ecran.sleep(1500);
		// on verifie si le joueur peut jouer (et quelles sont les cartes qu'il
		// peut jouer)
		int i = 0, max = this.cartes_main.size();
		boolean peut_jouer = false;
		this.cartes_jouables = new ArrayList<Carte>();
		Carte test = null;
		while (i < max) {
			test = this.cartes_main.get(i);
			if (test.jouable()) {
				this.cartes_jouables.add(test);
				peut_jouer = true;
			}
			i++;
		}
		// on lui propose les cartes s'il peut joueur
		if (peut_jouer) {
			ArrayList<Carte> cartes_jouables_avec_ce_nom;
			// on demande au joueur quelle carte il veut jouer
			new ChoisirCarteAJouer().faire(this);
			cartes_jouables_avec_ce_nom = this.cartesDeCeNomDansLaMain(
					this.cartes_jouables, this.nom_carte_choisie);
			this.nb_max_cartes_a_poser = cartes_jouables_avec_ce_nom.size();
			// on demande au joueur combien de cartes de ce nom il veut poser et
			// on le fait poser la carte
			if (this.nb_max_cartes_a_poser > 1) {
				// le joueur peut choisir combien de cartes il veut poser
				new ChoisirNbCartesAPoser().faire(this);
				// on pose les cartes du joueur
				Ecran.TOUR_joueurPoseCartes(this,
						cartes_jouables_avec_ce_nom.get(0),
						this.nb_cartes_a_poser);
				for (i = 0; i < this.nb_cartes_a_poser; i++) {
					this.jouerCarte(cartes_jouables_avec_ce_nom.get(0));
					cartes_jouables_avec_ce_nom.remove(0);
				}
			} else {
				// le joueur ne peut poser qu'une seule carte
				Ecran.TOUR_joueurPoseCartes(this,
						cartes_jouables_avec_ce_nom.get(0), 1);
				this.jouerCarte(cartes_jouables_avec_ce_nom.get(0));
			}
			// le joueur pioche
			int nb_piochages = 0;
			while (this.cartes_main.size() < 3
					&& Pioche.getInstance().getNbCartes() > 0) {
				this.piocher();
				nb_piochages++;
			}
			if (nb_piochages > 0) {
				Ecran.TOUR_joueurPiocheCartes(this, nb_piochages);
			}
		}
		// sinon on le fait ramasser le tas (on envoie le tas a lui meme)
		else {
			Ecran.TOUR_joueurRamasseLeTas(this, Tas.getInstance().getNbCartes());
			Tas.getInstance().envoyerTas(this);
			this.partie.reprendreMainApresRamassageDuTas();
		}
		// si le joueur n'a plus de cartes en main, et plus de cartes dans la
		// pioche, il ramasse LES TROIS cartes face visible
		if (this.cartes_main.size() == 0
				&& Pioche.getInstance().getNbCartes() == 0) {
			// s'il n'a plus de cartes visibles
			if (this.cartes_visibles.size() == 0) {
				// si le joueur a encore des cartes face cachee, il en ramasse
				// UNE
				if (this.cartes_cachees.size() > 0) {
					this.prendreCarteCachee();
				}
				// sinon alors le joueur n'a plus de carte et il a gagne ( se
				// fait dans Partie.tourDeJoueur() )
			}
			// sinon il ramasse LES TROIS cartes face visible d'un coup
			else {
				this.prendreCartesVisibles();
			}
		}
	}

	/**
	 * Le joueur échange ses cartes visibles avec les cartes de sa main
	 */
	public void echangerCartesVisiblesMain() {
		new EchangerCartesVisiblesMain().faire(this);
	}

	/**
	 * Le joueur envoie le tas de cartes à l'adversaire de son choix (après
	 * avoir posé un As)
	 */
	public void envoyerTasAAdversaire() {
		new EnvoyerTas().faire(this);

		Ecran.TOUR_envoyerTas(this, joueur_a_qui_on_envoie_tas, Tas
				.getInstance().getNbCartes());
		Tas.getInstance().envoyerTas(joueur_a_qui_on_envoie_tas);
	}

	/**
	 * On définit le nom de la carte choisie
	 * 
	 * @param nom
	 *            le nom de la carte qu'on souhaite poser
	 */
	public void setNomCarteChoisie(String nom) {
		this.nom_carte_choisie = nom.toLowerCase();
	}

	/**
	 * On définit le nombre de cartes de ce nom à poser
	 * 
	 * @param nb
	 *            le nombre de cartes qu'on veut poser
	 */
	public void setNbCartesAPoser(int nb) {
		this.nb_cartes_a_poser = nb;
	}

	/**
	 * On définit le joueur a qui on souhaite envoyer le tas
	 * 
	 * @param j
	 *            le joueur a qui on envoie le tas
	 */
	public void setJoueurAQuiEnvoyerLeTas(Joueur j) {
		this.joueur_a_qui_on_envoie_tas = j;
	}

	/**
	 * On récupère la liste des cartes jouables
	 * 
	 * @return la liste des cartes jouables
	 */
	public ArrayList<Carte> getCartesJouables() {
		return this.cartes_jouables;
	}

	/**
	 * On récupère le nombre max de cartes pouvant être posées
	 * 
	 * @return le nombre max de cartes pouvant être posées avec ce nom de carte
	 */
	public int getNbMaxCartesAPoser() {
		return this.nb_max_cartes_a_poser;
	}

}