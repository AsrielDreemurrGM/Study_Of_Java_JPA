package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Enrollment;

/**
 * DAO interface for managing {@link Enrollment} entities.
 *
 * <p>
 * Extends the generic contract from {@link IGenericDAO} and can be extended
 * with additional query methods specific to enrollments.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public interface IEnrollmentDAO extends IGenericDAO<Enrollment> {

}
