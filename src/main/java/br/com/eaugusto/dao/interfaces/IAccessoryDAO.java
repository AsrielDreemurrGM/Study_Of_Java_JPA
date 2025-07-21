package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Accessory;

/**
 * DAO interface for managing {@link Accessory} entities.
 *
 * <p>
 * Extends {@link IGenericDAO} to provide a contract for accessory-specific
 * persistence operations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public interface IAccessoryDAO extends IGenericDAO<Accessory> {

}
