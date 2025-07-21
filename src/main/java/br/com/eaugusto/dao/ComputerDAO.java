package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.IComputerDAO;
import br.com.eaugusto.domain.Computer;

/**
 * Concrete DAO class for {@link Computer} entities.
 *
 * <p>
 * Inherits base operations from {@link GenericDAO}, providing full persistence
 * support for {@link Computer} instances.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public class ComputerDAO extends GenericDAO<Computer> implements IComputerDAO {

}
