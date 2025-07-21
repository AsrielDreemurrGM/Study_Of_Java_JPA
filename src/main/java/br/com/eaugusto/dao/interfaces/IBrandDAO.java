package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Brand;

/**
 * DAO interface for managing {@link Brand} entities.
 *
 * <p>
 * Inherits CRUD method declarations from {@link IGenericDAO}, targeting the
 * {@link Brand} domain model.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public interface IBrandDAO extends IGenericDAO<Brand> {

}
