package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.domain.Enrollment;

/**
 * Concrete DAO class for {@link Enrollment} entities.
 *
 * <p>
 * Implements all generic CRUD operations by extending {@link GenericDAO}, with
 * {@link Enrollment} as the entity type.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class EnrollmentDAO extends GenericDAO<Enrollment> implements IEnrollmentDAO {

}
