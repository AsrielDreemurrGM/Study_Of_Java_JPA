package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.ICourseDAO;
import br.com.eaugusto.domain.Course;

/**
 * Concrete DAO class for {@link Course} entities.
 *
 * <p>
 * Provides standard CRUD operations through inheritance from
 * {@link GenericDAO}, using {@link Course} as the entity type.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class CourseDAO extends GenericDAO<Course> implements ICourseDAO {

}
