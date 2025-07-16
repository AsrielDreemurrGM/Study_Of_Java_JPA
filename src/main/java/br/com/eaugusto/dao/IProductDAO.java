package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Product;

/**
 * DAO interface for managing {@link Product} entities.
 *
 * <p>
 * Specialized version of {@link IGenericDAO} for product-related persistence
 * operations. May include custom product queries in future versions.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 16, 2025
 */
public interface IProductDAO extends IGenericDAO<Product> {

}
