package br.com.eaugusto.dao.generic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @param <T>
 * @since July 15, 2025
 */
public class GenericDAO<T> implements IGenericDAO<T> {

	@Override
	public T register(T entity) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return entity;
	}

	@Override
	public T searchById(Class<T> classType, Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		T entityFound = entityManager.find(classType, id);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return entityFound;
	}

	@Override
	public void delete(T entity) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}
}
