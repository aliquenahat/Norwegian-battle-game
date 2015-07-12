package fr.utt.lo02.paynatquenahat.joueurs;

import java.util.ArrayList;

import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.cartes.Carte;
import fr.utt.lo02.paynatquenahat.clavier.Clavier;
import fr.utt.lo02.paynatquenahat.exception.InvalidNbCartesAPoserException;
import fr.utt.lo02.paynatquenahat.paquet.Pioche;
import fr.utt.lo02.paynatquenahat.paquet.Tas;
import fr.utt.lo02.paynatquenahat.partie.*;

/**
 * Classe de gestion des joueurs physique, héritant de la classe Joueur
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class JoueurPhysique extends Joueur {

	/**
	 * Constructeur spécifique à la classe fille de Joueur (JoueurPhysique)
	 * 
	 * @param game
	 *            une instance de la Partie
	 * @param num
	 *            le numéro du joueur dans la partie
	 * @param name
	 *            le nom du joueur dans la partie
	 */
	public JoueurPhysique(Partie game, int num, NomJoueur name) {
		super(game, num, name, TypeJoueur.physique);
	}

	/**
	 * Le joueur joue son tour
	 */
	public void jouerTour() {
		// on trie les cartes du joueur
		this.trierCartesMain();
		Ecran.TOUR_textJoueurQuiJoue(this);
		// on verifie si le joueur peut jouer (et quelles sont les cartes qu'il
		// peut jouer)
		int i = 0, max = this.cartes_main.size();
		boolean peut_jouer = false;
		ArrayList<Carte> cartes_qui_peuvent_etre_jouees = new ArrayList<Carte>();
		Carte test = null;
		while (i < max) {
			test = this.cartes_main.get(i);
			if (test.jouable()) {
				cartes_qui_peuvent_etre_jouees.add(test);
				peut_jouer = true;
			}
			i++;
		}
		// on lui propose les cartes s'il peut joueur
		if (peut_jouer) {
			Ecran.TOUR_infosCartesPouvantEtreJouees(cartes_qui_peuvent_etre_jouees);
			ArrayList<Carte> cartes_jouables_avec_ce_nom;
			String nom_carte, text_derniere_carte;
			Carte derniere_carte_tas;
			int nb_max_cartes_a_poser = 0;
			// on demande au joueur quelle carte il veut jouer
			while (true) {
				derniere_carte_tas = Tas.getInstance().getDerniereCarte();
				text_derniere_carte = (derniere_carte_tas == null) ? ""
						: "(par dessus [" + derniere_carte_tas.getNom() + "])";
				nom_carte = Clavier
						.getCarteAJouer("\tQuel carte voulez-vous jouer "
								+ text_derniere_carte + "? ");
				cartes_jouables_avec_ce_nom = this.cartesDeCeNomDansLaMain(
						cartes_qui_peuvent_etre_jouees, nom_carte);
				nb_max_cartes_a_poser = cartes_jouables_avec_ce_nom.size();
				if (nb_max_cartes_a_poser > 0) {
					break;
				} else {
					Ecran.error("Vous ne pouvez pas jouer cette carte.");
				}
			}
			// on demande au joueur combien de cartes de ce nom il veut poser et
			// on le fait poser la carte
			if (nb_max_cartes_a_poser > 1) {
				// le joueur peut choisir combien de cartes il veut poser
				int nb_cartes_a_poser = 0;
				boolean nb_cartes_a_poser_OK = false;
				do {
					try {
						nb_cartes_a_poser = Clavier
								.getNbCartesAPoser(nb_max_cartes_a_poser);
						nb_cartes_a_poser_OK = true;
					} catch (InvalidNbCartesAPoserException e) {
						Ecran.error(e.getMessage());
					}
				} while (!nb_cartes_a_poser_OK);
				// on pose les cartes du joueur
				Ecran.TOUR_joueurPoseCartes(this,
						cartes_jouables_avec_ce_nom.get(0), nb_cartes_a_poser);
				for (i = 0; i < nb_cartes_a_poser; i++) {
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
			Ecran.appuyezEntrer();
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
	 * Le joueur échange ses cartes face visible avec les cartes de sa main
	 */
	public void echangerCartesVisiblesMain() {
		// on demande a l'utilisateur s'il veut echanger ses cartes
		String saisie;
		boolean saisie_ok = false;
		boolean veut_echanger = false;
		do {
			saisie = Ecran
					.prompt("Voulez vous echanger vos cartes face visibles avec les cartes de votre main ? [o/n] ");
			if (saisie.equals("o")) {
				veut_echanger = true;
				saisie_ok = true;
			} else {
				if (saisie.equals("n")) {
					veut_echanger = false;
					saisie_ok = true;
				} else {
					Ecran.error("Vous devez entrer \"o\" ou \"n\" pour faire votre choix.");
				}
			}
		} while (!saisie_ok);
		// s'il veut echanger alors on lance le processus
		if (veut_echanger) {
			Ecran.infosCartesAEchangerDebutPartie(this);

			String choix;
			int index_carte_v, index_carte_m;
			boolean index_v_OK, index_m_OK, veut_continuer;
			do {
				index_v_OK = false;
				index_m_OK = false;
				veut_continuer = false;
				// on demande la carte visible a echanger
				do {
					index_carte_v = Clavier
							.getInt("\nCarte visible a echanger (numero): ");
					if (index_carte_v >= 1 && index_carte_v <= 3) {
						index_v_OK = true;
					}
				} while (!index_v_OK);
				// on demande la carte de la main a echanger
				do {
					index_carte_m = Clavier
							.getInt("\nCarte de la main a echanger (numero): ");
					if (index_carte_m >= 4 && index_carte_m <= 6) {
						index_m_OK = true;
					}
				} while (!index_m_OK);
				// on echange les deux cartes
				this.permuteCartesVisiblesMain(this.cartes_visibles,
						index_carte_v - 1, this.cartes_main, index_carte_m - 4);
				Ecran.infosCartesAEchangerDebutPartie(this);
				// on demande a l'utilisateur s'il veut continuer a changer ses
				// cartes
				choix = Clavier.getText("##=> Avez vous termine ? [o/n] ");
				if (!choix.equals("o"))
					veut_continuer = true;
			} while (veut_continuer);
		}
	}

	/**
	 * Le joueur envoie le tas à un adversaire de son choix
	 */
	public void envoyerTasAAdversaire() {
		// on affiche les autres joueurs a qui on peut envoyer le tas !
		ArrayList<Joueur> autres_joueurs = this.getAutresJoueurs();
		Ecran.infosAutresJoueursEnvoyerTas(autres_joueurs);
		int index_max = this.partie.getNbJoueurs() - 1, index_joueur;
		boolean index_joueur_OK = false;

		// on demande au joueur a qui il veut envoyer le Tas (la liste vient
		// d'etre affichee)
		// on recupere le numero du joueur en question
		do {
			index_joueur = Clavier.getInt("\tEnvoyer le Tas a : ");
			if (index_joueur > 0
					&& index_joueur <= index_max
					&& autres_joueurs.get(index_joueur - 1).getNumero() != this
							.getNumero()) {
				index_joueur_OK = true;
			} else {
				Ecran.error("Le numero ne correspond a aucun joueur.");
			}
		} while (!index_joueur_OK);

		// on envoie le Tas au joueur correspondant
		Joueur joueur_a_qui_on_envoie_tas = autres_joueurs
				.get(index_joueur - 1);
		Ecran.TOUR_envoyerTas(this, joueur_a_qui_on_envoie_tas, Tas
				.getInstance().getNbCartes());
		Tas.getInstance().envoyerTas(joueur_a_qui_on_envoie_tas);
	}

}