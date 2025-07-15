package br.com.eaugusto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.eaugusto.domain.Course;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class CourseDAO implements ICourseDAO {

	@Override
	public Course register(Course course) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(course);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return course;
	}

}
