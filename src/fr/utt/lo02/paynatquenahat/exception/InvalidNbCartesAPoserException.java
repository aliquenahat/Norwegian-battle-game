package fr.utt.lo02.paynatquenahat.exception;

/**
 * Exception g�rant le probl�me d'une carte invalide
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class InvalidNbCartesAPoserException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Affectation d'un message � l'exception
	 * 
	 * @return Le message
	 */
	public String getMessage() {
		return "Nombre invalide.";
	}

}