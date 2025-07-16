package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Course;

/**
 * DAO interface for managing {@link Course} entities.
 *
 * <p>
 * Extends the generic CRUD operations provided by {@link IGenericDAO} and may
 * be expanded with course-specific queries in the future.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public interface ICourseDAO extends IGenericDAO<Course> {

}
