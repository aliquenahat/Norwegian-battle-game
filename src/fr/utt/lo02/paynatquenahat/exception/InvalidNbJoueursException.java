package fr.utt.lo02.paynatquenahat.exception;

import fr.utt.lo02.paynatquenahat.configuration.Config;

/**
 * Exception g�rant le nombre de joueurs max
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class InvalidNbJoueursException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Affectation d'un message � l'exception
	 * 
	 * @return Le message
	 */
	public String getMessage() {
		return "Vous devez choisir un nombre entre 2 et "
				+ Config.NB_MAX_JOUEURS() + ".";
	}

}