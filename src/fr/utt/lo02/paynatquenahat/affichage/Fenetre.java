package fr.utt.lo02.paynatquenahat.affichage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import fr.utt.lo02.paynatquenahat.cartes.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.paquet.*;

/**
 * Classe de gestion de la fenêtre
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Fenetre implements Affichage {

	// Liste des attributs
	private JFrame fenetre = new JFrame("Projet LO02");
	private JTextField champ = new JTextField();
	private JLabel titre = new JLabel("Bataille norvegienne");
	private JLabel infos = new JLabel("Informations");
	private JLabel question = new JLabel();
	private JLabel main = new JLabel();
	private JLabel historique = new JLabel("Historique");
	private JLabel joueur = new JLabel("Joueurs");
	private JLabel copyright = new JLabel(
			"Victor PAYNAT-SAUTIVET .:. Ali QUENAHAT .:. Pour LO02");
	private JLabel erreur = new JLabel();
	private JLabel imageAs = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("as.png")));
	private JLabel image = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("carte.png")));
	private JLabel norvege = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("norvege.png")));
	private JButton entrer = new JButton("Entrer");
	private JTextArea textArea1 = new JTextArea();
	private JTextArea textArea2 = new JTextArea("La partie commence.\n");
	private JTextArea textArea3 = new JTextArea();
	private static Affichage _instance = null;

	/**
	 * Il s'agit d'un singleton
	 */
	private Fenetre() {
	}

	/**
	 * Obtention de l'instance d'affichage
	 * 
	 * @return Retourne l'instance
	 */
	public static Affichage getInstance() {
		if (_instance == null) {
			_instance = new Fenetre();
		}
		return _instance;
	}

	/**
	 * Affiche le début du programme
	 */
	public void demarrage() {

		// Edition des composants
		fenetre.setIconImage( new ImageIcon(getClass().getClassLoader().getResource("carte.png")).getImage() );
		fenetre.setContentPane(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fond.jpg"))));
		infos.setForeground(Color.WHITE);
		infos.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		historique.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		joueur.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		main.setForeground(Color.WHITE);
		titre.setFont(new Font("Arial", Font.BOLD, 32));
		titre.setForeground(Color.WHITE);
		historique.setForeground(Color.WHITE);
		joueur.setForeground(Color.WHITE);
		question.setForeground(Color.WHITE);
		erreur.setForeground(Color.WHITE);
		copyright.setForeground(Color.WHITE);
		copyright.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
		champ.setBackground(new Color(159, 232, 85));
		Border greenline = BorderFactory
				.createLineBorder(new Color(34, 120, 15));
		champ.setBorder(greenline);
		champ.setMargin(new Insets(2, 2, 2, 2));
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(false);
		textArea1.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		textArea1.setForeground(Color.WHITE);
		textArea1.setBackground(null);
		textArea1.setOpaque(false);

		textArea2.setEditable(false);
		textArea2.setLineWrap(true);
		textArea2.setMargin(new Insets(3, 3, 3, 3));
		textArea2.setWrapStyleWord(false);
		textArea2.setBackground(new Color(159, 232, 85));
		JScrollPane scroll = new JScrollPane(textArea2,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(greenline);

		textArea3.setEditable(false); // set textArea non-editable
		textArea3.setLineWrap(true);
		textArea3.setWrapStyleWord(false);
		textArea3.setMargin(new Insets(3, 3, 3, 3));
		textArea3.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		textArea3.setForeground(Color.WHITE);
		JScrollPane scroll3 = new JScrollPane(textArea3,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea3.setOpaque(false);
		scroll3.setOpaque(false);
		scroll3.getViewport().setOpaque(false);
		scroll3.setBorder(null);
		scroll3.setViewportBorder(null);

		DefaultCaret caret = (DefaultCaret) textArea2.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll.setBounds(180, 90, 400, 260);
		question.setBounds(180, 368, 600, 25);
		titre.setBounds(270, 3, 390, 50);
		norvege.setBounds(330, 4, 128, 128);
		copyright.setBounds(250, 473, 630, 12);
		textArea1.setBounds(10, 90, 150, 260);
		scroll3.setBounds(600, 90, 284, 247);
		historique.setBounds(180, 60, 450, 15);
		joueur.setBounds(600, 60, 250, 10);
		infos.setBounds(10, 60, 150, 10);
		champ.setBounds(180, 419, 320, 25);
		entrer.setBounds(501, 419, 79, 24);
		main.setBounds(180, 353, 400, 19);
		erreur.setBounds(180, 390, 830, 19);
		image.setBounds(730, 340, 128, 128);
		imageAs.setBounds(10, 340, 128, 128);
		fenetre.setLayout(null);

		// Ajout des composants
		fenetre.add(textArea1);
		fenetre.add(copyright);
		fenetre.add(titre);
		fenetre.add(scroll);
		fenetre.add(scroll3);
		fenetre.add(infos);
		fenetre.add(champ);
		fenetre.add(entrer);
		fenetre.add(question);
		fenetre.add(norvege);
		fenetre.add(erreur);
		entrer.addActionListener(new EntrerListener());
		fenetre.add(joueur);
		fenetre.add(historique);
		fenetre.add(main);
		fenetre.add(image);
		fenetre.add(imageAs);

		// Quitter le programme lorsqu'on ferme la fenêtre
		fenetre.setSize(900, 530);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		joueur.setText("Joueurs (" + joueurs.size() + ")");
		this.text((joueurs.size() - 1) + " joueur(s) virtuel(s) : \n");

		boolean first = true;
		while (it.hasNext()) {
			j = it.next();
			if (j.getTypeJoueur() == TypeJoueur.virtuel) {
				if (!first) {
					this.text("\n");
				}
				this.text("- " + j.getNom());
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
		this.textln(result + " de distribuer les cartes.\n");
		result = joueurs.get(0).getNom() + " ";
		result = (joueurs.get(0).getTypeJoueur() == TypeJoueur.physique) ? result
				+ "commencerez"
				: result + "commencera";
		this.textln(result + " a jouer.\n");
		// infos cartes distribuees aux joueurs
		this.infosTourTable(joueurs, 0, false);
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
		this.textln("\nCartes Visibles :");
		for (int i = 0; i < 3; i++) {
			this.text("         " + (i + 1) + ")");
			this.afficheCarte(visibles.get(i).getNom());
		}
		this.textln("\nMain :");
		for (int i = 0; i < 3; i++) {
			this.text("         " + (i + 4) + ")");
			this.afficheCarte(main.get(i).getNom());
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
		// nb de cartes sur le tas et dans la pioche
		Tas tas = Tas.getInstance();

		Pioche pioche = Pioche.getInstance();
		if (titre_tour) {
			this.text("\n\nTour " + tour_actuel + " :\n");
			textArea1.setText("Tour : " + tour_actuel + "\nTas : "
					+ tas.getNbCartes() + " carte(s)\nPioche : "
					+ pioche.getNbCartes() + " carte(s)\n");

		} else {
			textArea1.setText("Tas : " + tas.getNbCartes()
					+ " carte(s)\nPioche : " + pioche.getNbCartes()
					+ " carte(s)\n");

		}

		// infos sur les joueurs
		Iterator<Joueur> it = joueurs.iterator();

		textArea3.setText("");
		while (it.hasNext()) {
			this.infosJoueur(it.next());
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
		textArea3.setText(textArea3.getText() + j.getNom() + " :    ");
		// affichage carte face visible

		it = j.getCartesVisibles().iterator();
		while (it.hasNext()) {
			temp = it.next();
			this.afficheCarteVisible(temp.getNom());
		}

		textArea3.setText(textArea3.getText() + "  ");
		// affichage carte face cachee
		it = j.getCartesCachees().iterator();
		while (it.hasNext()) {
			this.afficheCarteVisible(".");
			it.next();
		}
		// affichage nombre de carte en main

		it = j.getCartesMain().iterator();
		if (j.getTypeJoueur() == TypeJoueur.physique) {
			main.setText("Main : ");
			// si c'est le joueur physique alors on affiche ses cartes
			while (it.hasNext()) {
				this.afficheCarteMain(it.next().getNom());
			}
		}
		textArea3.setText(textArea3.getText() + "\n");
	}

	/**
	 * Affiche une carte à l'écran pour traiter l'affichage de la main
	 * 
	 * @param text
	 *            Le nom de la carte
	 */
	private void afficheCarteMain(String text) {
		main.setText(main.getText() + "[" + text + "]");
	}

	/**
	 * Affiche une carte à l'écran pour traiter l'affichage général
	 * 
	 * @param text
	 *            Le nom de la carte
	 */
	private void afficheCarte(String text) {
		this.text("[" + text + "]");
	}

	/**
	 * Affiche une carte à l'écran pour traiter l'affichage des cartes visibles
	 * 
	 * @param text
	 *            Le nom de la carte
	 */
	private void afficheCarteVisible(String text) {
		textArea3.setText(textArea3.getText() + "[" + text + "]");
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

		question.setText("Veuillez choisir le joueur a qui envoyer le Tas : (son numero)");
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

		this.textln("\n" + joueur.getNom() + " " + lang + " tour.\n");
	}

	/**
	 * Affichage du joueur qui est entrain de joueur
	 * 
	 * @param joueur
	 *            Le joueur concerné
	 */
	public void TOUR_textJoueurQuiJoue(Joueur joueur) {
		this.textln("C'est a " + joueur.getNom() + " de jouer.\n");
	}

	/**
	 * Affiche les cartes que le joueur physique peut jouer
	 * 
	 * @param cartes
	 *            Liste des cartes
	 */
	public void TOUR_infosCartesPouvantEtreJouees(ArrayList<Carte> cartes) {
		this.text("Vos cartes jouables : ");
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
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "posez "
				: "pose ";

		this.text(joueur.getNom() + " " + lang + nb_a_poser + "x");
		this.afficheCarte(carte.getNom());
		this.text("\n");
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
				+ " carte(s).\n");
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
		lang = (joueur.getTypeJoueur() == TypeJoueur.physique) ? "ne pouvez pas jouer, et ramassez le tas de "
				: "ne peut pas jouer, et ramasse le tas de ";

		this.textln(joueur.getNom() + " " + lang + nb_cartes_ramassees
				+ " carte(s).\n");
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

		this.textln(joueur.getNom() + " " + lang + " cartes face cachee.\n");
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

		this.textln(envoyeur.getNom() + " " + lang + " le tas a "
				+ receveur.getNom() + " (" + nb_cartes + " carte(s)).\n");
	}

	/**
	 * Affiche un message quand un 7 est posé
	 */
	public void TOUR_7pose() {
		this.textln("La prochaine carte posee doit avoir une valeur inferieure a 7.\n");
	}

	/**
	 * Affiche un message quand un 10 est posé
	 */
	public void TOUR_10pose() {
		this.textln("Les cartes du tas sont retirees du jeu.\n");
	}

	/**
	 * Affiche les infos a la fin de la partie
	 * 
	 * @param joueur
	 *            Le joueur vainqueur
	 */
	public void afficheFinDePartie(Joueur vainqueur) {
		this.textln("Le Danish est " + vainqueur.getNom()
				+ " car il a gagné la partie.");
	}

	/**
	 * Demande a l'utilisateur de saisir quelque chose au clavier
	 * 
	 * @param msg
	 *            Le message qui attends une réponse
	 * @return Retourne la saisie correspondante
	 */
	public String prompt(String msg) {
		String saisie = "";
		if (champ.getText() != null) {
			question.setText(msg);
			saisie = champ.getText();
		}
		return saisie;
	}

	/*
	 * Affichage de texte PRIVATE
	 */

	/**
	 * Affiche une ligne de texte
	 * 
	 * @param msg
	 *            Le message concerné
	 */
	public void textln(String msg) {
		textArea2.setText(textArea2.getText() + msg);

	}

	/**
	 * Affiche du texte sans retour a la ligne
	 * 
	 * @param msg
	 *            Le message concerné
	 */
	public void text(String msg) {
		textArea2.setText(textArea2.getText() + msg);
	}

	/**
	 * Affichage d'une action (ex: distribuer les cartes)
	 * 
	 * @param msg
	 *            L'action concernée
	 */
	public void actionDebut(String msg) {
		textArea2.setText(textArea2.getText());
	}

	/**
	 * Affiche un morceau de chargement (un "." a chaque avancement du
	 * chargement)
	 */
	public void chargementSupp() {

	}

	/**
	 * Termine l'affichage d'une action
	 */
	public void actionFin() {
	}

	/**
	 * Affiche un message "appuyez sur entrer pour continuer"
	 */
	public void appuyezEntrer() {

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
	}

	/**
	 * Affiche une erreur venue de l'utilisateur ( d'ou la difference avec
	 * bug())
	 * 
	 * @param Le
	 *            message de l'erreur
	 */
	public void error(String msg) {

	}

	/*
	 * Special
	 */

	/**
	 * Mets le programme en pause, attend que l'utilisateur appuye sur [entrer]
	 */
	public void pause() {

	}

	/**
	 * La classe gérant l'évènement du bouton entrer
	 * 
	 * @author Ali QUENAHAT, Victor PAYNAT-SAUTIVET
	 * @version 1.0
	 */
	class EntrerListener implements ActionListener {
		/**
		 * La méthode gérant le traitement à faire
		 * 
		 * @param arg0
		 *            L'action concernée
		 */
		public void actionPerformed(ActionEvent arg0) {
			champ.setText("");
		}
	}
}