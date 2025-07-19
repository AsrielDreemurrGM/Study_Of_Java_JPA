package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.IProductDAO;
import br.com.eaugusto.domain.Product;

/**
 * Concrete DAO class for {@link Product} entities.
 *
 * <p>
 * Implements the {@link IProductDAO} interface by extending {@link GenericDAO},
 * inheriting basic JPA-based CRUD functionality for the {@link Product} entity.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 16, 2025
 */
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

}
