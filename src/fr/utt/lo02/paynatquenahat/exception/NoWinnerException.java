package fr.utt.lo02.paynatquenahat.exception;

/**
 * Exception traitant le cas ou aucun joueur ne gagne
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class NoWinnerException extends Exception {

	private static final long serialVersionUID = 3L;

	/**
	 * Affectation d'un message à l'exception
	 * 
	 * @return Le message
	 */
	public String getMessage() {
		return "La partie ne contient aucun vainqueur.";
	}

}