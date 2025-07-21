package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generic.GenericDAO;
import br.com.eaugusto.dao.interfaces.IStudentDAO;
import br.com.eaugusto.domain.Student;

/**
 * Concrete DAO class for {@link Student} entities.
 *
 * <p>
 * Uses {@link GenericDAO} to provide JPA-based CRUD operations for managing
 * {@link Student} records.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public class StudentDAO extends GenericDAO<Student> implements IStudentDAO {

}
