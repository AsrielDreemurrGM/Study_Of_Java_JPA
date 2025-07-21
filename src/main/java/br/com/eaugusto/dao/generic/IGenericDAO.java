package br.com.eaugusto.dao.generic;

import java.util.List;

/**
 * Generic DAO interface defining basic CRUD operations using JPA.
 *
 * <p>
 * Declares common persistence operations for any entity type {@code T}.
 * Intended to be implemented by concrete DAO classes.
 * </p>
 *
 * @param <T> The type of the entity being managed.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 *
 * @see javax.persistence.EntityManager
 * @see GenericDAO
 */
public interface IGenericDAO<T> {

	/**
	 * Persists a new entity to the database.
	 *
	 * @param entity The entity to be registered.
	 * @return The persisted entity with generated fields (e.g., ID).
	 */
	public T register(T entity);

	/**
	 * Finds an entity by its primary key (ID).
	 *
	 * @param classType The class of the entity.
	 * @param id        The ID to search for.
	 * @return The found entity or {@code null} if not found.
	 */
	public T searchById(Class<T> classType, Long id);

	/**
	 * Retrieves all instances of the given entity type from the database.
	 *
	 * @param classType The class of the entity.
	 * @return A list of all entities, or an empty list if none are found.
	 */
	public List<T> searchAll(Class<T> classType);

	/**
	 * Updates an existing entity in the database.
	 *
	 * @param entityWithNewInformation The entity with updated data.
	 * @return The updated managed entity.
	 */
	public T update(T entityWithNewInformation);

	/**
	 * Removes an entity from the database.
	 *
	 * @param entity The entity to be deleted.
	 */
	public void delete(T entity);
}
