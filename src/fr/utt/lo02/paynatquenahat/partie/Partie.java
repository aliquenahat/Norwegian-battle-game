package fr.utt.lo02.paynatquenahat.partie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import fr.utt.lo02.paynatquenahat.exception.*;
import fr.utt.lo02.paynatquenahat.affichage.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.paquet.*;
import fr.utt.lo02.paynatquenahat.cartes.*;

/**
 * Classe de gestion des parties
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Partie {

	// attributs
	private int nb_joueurs;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private ArrayList<NomJoueur> noms_dispos;
	private JeuCartes jeuCartes;

	private int tourActuel = 1;
	private int prochain_joueur_passe_son_tour = 0;
	private boolean reprise_apres_ramassage_du_tas = false;

	private boolean terminee = false;
	private Joueur vainqueur;

	/**
	 * Crée la partie en fonction du nombre de joueurs (tout est créé dans ce
	 * controleur : paquet, joueurs)
	 * 
	 * @param nb_de_joueurs
	 */
	public Partie(int nb_de_joueurs) {
		// on definit le nombre de joueurs
		this.setNbJoueurs(nb_de_joueurs);

		// Creation du paquet qui contient toutes les cartes
		this.jeuCartes = new JeuCartes(this.getNbJoueurs());

		// Creation du joueur physique
		JoueurPhysique vous = new JoueurPhysique(this, 1, NomJoueur.Vous);
		this.joueurs.add(vous);
		this.noms_dispos = new ArrayList<NomJoueur>(Arrays.asList(NomJoueur
				.values()));
		this.noms_dispos.remove(0); // on retire le nom "Vous"

		// Creation des joueurs virtuels
		Collections.shuffle(this.noms_dispos); // on melange les noms
		for (int i = 0; i < nb_de_joueurs - 1; i++) {
			this.addJoueurVirtuel(i + 2);
		}
	}

	/**
	 * Affiche le nom des joueurs virtuels
	 */
	public void afficheNomsJoueursVirtuels() {
		Ecran.infosJoueursAvantPartie(this.joueurs);
	}

	/**
	 * Dispose aléatoirement les joueurs autour de la table (on sait ainsi quel
	 * joueur a distribué et lequel va commencer à jouer)
	 */
	private void disposerLesJoueursAutourDeLaTable() {
		// on melange la collection de joueurs
		Collections.shuffle(this.joueurs);

		// on reattribut le bon numero a chaque joueur
		Iterator<Joueur> it = this.joueurs.iterator();
		Joueur j;
		int num = 1;
		while (it.hasNext()) {
			j = it.next();
			j.setNumero(num);
			num++;
		}

		// => le dernier joueur de la collection est celui qui distribue les
		// cartes
		// => le premier joueur de la collection est celui qui commence a jouer
	}

	/**
	 * On distribue les cartes aux joueurs (une par une) On met les cartes qui
	 * restent dans la pioche
	 */
	private void distribuerLesCartes() {
		Ecran.actionDebut("Distribution des cartes");

		// on melange les cartes et on les recupere
		this.jeuCartes.melangerCartes();
		ArrayList<Carte> cartes = this.jeuCartes.getCartes();

		// on distribue les cartes une a une
		int i, j;
		// #-Cartes face cachee
		for (i = 0; i < 3; i++) { // on ajoute trois cartes
			// on parcourt les joueurs
			for (j = 0; j < this.joueurs.size(); j++) {
				// on ajoute une carte face cachee au joueur actuel
				this.joueurs.get(j).donnerCarteCachee(cartes.get(0));
				cartes.remove(0);
			}
			Ecran.chargementSupp();
			Ecran.sleep(150);
		}
		// #-Cartes face visible
		for (i = 0; i < 3; i++) {
			for (j = 0; j < this.joueurs.size(); j++) {
				// on ajoute une carte face visible au joueur actuel
				this.joueurs.get(j).donnerCarteVisible(cartes.get(0));
				cartes.remove(0);
			}
			Ecran.chargementSupp();
			Ecran.sleep(150);
		}
		// #-Cartes dans la main
		for (i = 0; i < 3; i++) {
			for (j = 0; j < this.joueurs.size(); j++) {
				// on ajoute une carte dans la main du joueur actuel
				this.joueurs.get(j).donnerCarteMain(cartes.get(0));
				cartes.remove(0);
			}
			Ecran.chargementSupp();
			Ecran.sleep(150);
		}

		// on cree la pioche avec les cartes restantes
		Pioche pioche = Pioche.getInstance();
		Carte card;
		while (!cartes.isEmpty()) {
			card = cartes.get(0);
			pioche.ajouterCarte(card);
			cartes.remove(0);
		}

		Ecran.actionFin();
	}

	/**
	 * Les joueurs peuvent échanger leur cartes face visible avec les cartes de
	 * leur main.
	 */
	private void echangerCartesDebutPartie() {
		// proposition d'echange des cartes
		Iterator<Joueur> it = this.joueurs.iterator();
		while (it.hasNext()) {
			it.next().echangerCartesVisiblesMain();
		}
	}

	/**
	 * Méthode principale permettant de lancer la partie et de déclencher les
	 * autres évènements.
	 */
	public void launch() {
		// on dispose les joueurs autour de la table
		this.disposerLesJoueursAutourDeLaTable();

		// on distribue les cartes
		this.distribuerLesCartes();
		Ecran.infosJoueursDebutPartie(this.joueurs);

		// actions d'avant partie : echanger les cartes visibles avec les cartes
		// de la main
		this.echangerCartesDebutPartie();

		// on demarre les tours de table
		do {
			this.tourDeTable();
		} while (!this.terminee);
	}

	/**
	 * On effectue un tour de table (chaque joueur joue à son tour)
	 */
	private void tourDeTable() {
		// on affiche les infos de la partie au debut du tour
		Ecran.infosTourTable(this.joueurs, this.tourActuel, true);

		// pause : appuyez sur Entrer
		Ecran.appuyezEntrer();

		// on fait jouer chaque joueur chacun leur tour
		Joueur j_qui_doit_jouer;
		int i = 0;
		while (i < this.joueurs.size() && !this.terminee) {
			j_qui_doit_jouer = this.joueurs.get(i);
			// on fait jouer le joueur
			this.tourDeJoueur(j_qui_doit_jouer);
			// si on doit reprendre apres un ramassage du tas
			if (this.reprise_apres_ramassage_du_tas) {
				// on refait jouer le dernier joueur qui a pose une carte
				if (i == 0) {
					i = this.joueurs.size() - 1;
				} else {
					i--;
				}
				this.reprise_apres_ramassage_du_tas = false;
			} else {
				// sinon on passe au joueur suivant
				i++;
			}
		}
		this.tourActuel++;
	}

	/**
	 * On fait jouer un joueur (à chaque tour de table)
	 * 
	 * @param j_qui_joue
	 *            joueur qui doit jouer son tour (objet de type Joueur)
	 */
	private void tourDeJoueur(Joueur j_qui_joue) {
		// on fait jouer le joueur
		if (this.prochain_joueur_passe_son_tour > 0) {
			this.prochain_joueur_passe_son_tour--;
			Ecran.TOUR_joueurPasseSonTour(j_qui_joue);
			return;
		} else {
			j_qui_joue.jouerTour();
		}

		// on verifie que le joueur a encore des cartes
		if (j_qui_joue.getNbCartes() <= 0) {
			this.terminee = true;
			this.vainqueur = j_qui_joue;
		}
	}

	/**
	 * Permet de faire passer son tour au jour suivant Cette méthode est appelée
	 * autant de fois que de 8 posés et donc autant de joueurs passeront leur
	 * tour.
	 */
	public void prochainJoueurPasseSonTour() {
		this.prochain_joueur_passe_son_tour++;
	}

	/**
	 * Permet au dernier joueur qui a posé de reprendre la main après qu'un
	 * joueur doive ramasser le tas.
	 */
	public void reprendreMainApresRamassageDuTas() {
		this.reprise_apres_ramassage_du_tas = true;
	}

	/**
	 * Crée un joueur virtuel (appelée autant de fois que de joueurs virtuels à
	 * créer)
	 * 
	 * @param num
	 *            Numéro du joueur
	 */
	public void addJoueurVirtuel(int num) {
		// Selection du nom du joueur
		NomJoueur newnom = this.noms_dispos.get(0);
		this.noms_dispos.remove(0);

		// ajout du joueur a la liste
		JoueurVirtuel newjoueur = new JoueurVirtuel(this, num, newnom);
		this.joueurs.add(newjoueur);
	}

	/**
	 * Définit le nombre de joueurs pour cette partie
	 * 
	 * @param nb
	 *            Le nombre de joueurs dans cette partie (choisit par
	 *            l'utilisateur au début)
	 */
	private void setNbJoueurs(int nb) {
		this.nb_joueurs = nb;
	}

	/**
	 * Retourne le nombre de joueurs de la partie
	 * 
	 * @return Le nombre de joueurs
	 */
	public int getNbJoueurs() {
		return this.nb_joueurs;
	}

	/**
	 * Retourne la liste des joueurs de la partie (objets de type Joueur)
	 * 
	 * @return une ArrayList de Joueur
	 */
	public ArrayList<Joueur> getListeJoueurs() {
		return this.joueurs;
	}

	/**
	 * Retourne le joueur qui a gagné la partie
	 * 
	 * @return joueur vainqueur
	 * @throws NoWinnerException
	 */
	public Joueur getVainqueur() throws NoWinnerException {
		if (this.vainqueur != null) {
			return this.vainqueur;
		}
		throw new NoWinnerException();
	}

}