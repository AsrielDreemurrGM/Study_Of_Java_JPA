package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Car;

/**
 * DAO interface for managing {@link Car} entities.
 *
 * <p>
 * Extends {@link IGenericDAO} to define persistence operations specific to the
 * {@link Car} domain model.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public interface ICarDAO extends IGenericDAO<Car> {

}
