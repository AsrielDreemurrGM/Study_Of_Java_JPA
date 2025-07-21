package br.com.eaugusto.dao.interfaces;

import br.com.eaugusto.dao.generic.IGenericDAO;
import br.com.eaugusto.domain.Student;

/**
 * DAO interface for managing {@link Student} entities.
 *
 * <p>
 * Defines persistence operations for {@link Student} by extending
 * {@link IGenericDAO}.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public interface IStudentDAO extends IGenericDAO<Student> {

}
