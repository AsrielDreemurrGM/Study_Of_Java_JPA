package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.IAccessoryDAO;
import br.com.eaugusto.domain.Accessory;

/**
 * Concrete DAO class for {@link Accessory} entities.
 *
 * <p>
 * Inherits generic CRUD operations from {@link GenericDAO}, specifically for
 * {@link Accessory} persistence.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class AccessoryDAO extends GenericDAO<Accessory> implements IAccessoryDAO {

}
