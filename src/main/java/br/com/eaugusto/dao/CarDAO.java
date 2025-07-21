package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.ICarDAO;
import br.com.eaugusto.domain.Car;

/**
 * Concrete DAO class for {@link Car} entities.
 *
 * <p>
 * Uses {@link GenericDAO} to perform standard persistence operations for
 * {@link Car} entities.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class CarDAO extends GenericDAO<Car> implements ICarDAO {

}
