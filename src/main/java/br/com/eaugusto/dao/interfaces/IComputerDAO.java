package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Computer;

/**
 * DAO interface for managing {@link Computer} entities.
 *
 * <p>
 * Declares persistence methods for the {@link Computer} model using the generic
 * {@link IGenericDAO} abstraction.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public interface IComputerDAO extends IGenericDAO<Computer> {

}
