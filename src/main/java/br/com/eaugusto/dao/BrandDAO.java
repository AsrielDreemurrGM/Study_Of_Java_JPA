package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.IBrandDAO;
import br.com.eaugusto.domain.Brand;

/**
 * Concrete DAO class for {@link Brand} entities.
 *
 * <p>
 * Provides access to basic persistence operations inherited from
 * {@link GenericDAO}, using {@link Brand} as the entity type.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class BrandDAO extends GenericDAO<Brand> implements IBrandDAO {

}
