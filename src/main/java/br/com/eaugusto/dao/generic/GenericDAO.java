package br.com.eaugusto.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Default implementation of {@link IGenericDAO} using JPA.
 *
 * <p>
 * This generic DAO handles standard CRUD operations for any entity type
 * {@code T}, leveraging {@link EntityManager} and JPA for persistence.
 * </p>
 *
 * <p>
 * The configured persistence unit name is {@code Study_Of_Java_JPA}, defined in
 * {@code persistence.xml}.
 * </p>
 *
 * @param <T> The type of entity managed by this DAO.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class GenericDAO<T> implements IGenericDAO<T> {

	private static final String PROJECTNAME = "Study_Of_Java_JPA";

	@Override
	public T register(T entity) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PROJECTNAME);
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PROJECTNAME);
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PROJECTNAME);
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PROJECTNAME);
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PROJECTNAME);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}
}
