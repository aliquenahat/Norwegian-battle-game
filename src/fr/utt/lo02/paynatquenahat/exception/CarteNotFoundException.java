package fr.utt.lo02.paynatquenahat.exception;

/**
 * Exception pour les cartes non trouv�es
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class CarteNotFoundException extends Exception {

	private static final long serialVersionUID = 2L;

	/**
	 * Affectation d'un message � l'exception
	 * 
	 * @return Le message
	 */
	public String getMessage() {
		return "La carte est introuvable dans cet ensemble.";
	}

}