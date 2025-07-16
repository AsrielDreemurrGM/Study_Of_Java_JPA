package br.com.eaugusto.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Default implementation of {@link IGenericDAO} using JPA.
 *
 * <p>
 * This class handles generic CRUD operations for any entity type {@code T}. It
 * manages transactions and life cycle operations through {@link EntityManager}
 * instances.
 * </p>
 *
 * <p>
 * The persistence unit used is {@code Study_Of_Java_JPA}, as defined in
 * {@code persistence.xml}.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 * 
 * @param <T> The entity type managed by this DAO implementation.
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
	public List<T> searchAll(Class<T> classType) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		String entityName = classType.getSimpleName();

		List<T> list = entityManager.createQuery("SELECT e FROM " + entityName + " e", classType).getResultList();
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return list;
	}

	@Override
	public T update(T entityWithNewInformation) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Study_Of_Java_JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		T updatedEntity = entityManager.merge(entityWithNewInformation);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return updatedEntity;
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
