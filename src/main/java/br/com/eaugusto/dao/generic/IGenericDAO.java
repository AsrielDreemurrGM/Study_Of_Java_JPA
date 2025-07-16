package br.com.eaugusto.dao.generic;

import java.util.List;

/**
 * Generic Data Access Object interface for basic CRUD operations.
 *
 * <p>
 * This interface defines the contract for managing entities of type {@code T}
 * using Java Persistence API (JPA). It abstracts the common operations such as
 * registering, updating, searching, and deleting entities.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 * 
 * @param <T> The type of entity to be managed.
 * @see javax.persistence.EntityManager
 * @see GenericDAO
 */
public interface IGenericDAO<T> {

	/**
	 * Persists a new entity in the database.
	 *
	 * @param entity The entity to register.
	 * @return The persisted entity with generated fields populated (e.g. ID).
	 */
	public T register(T entity);

	/**
	 * Retrieves an entity by its ID.
	 *
	 * @param classType The class of the entity.
	 * @param id        The ID of the entity.
	 * @return The found entity, or {@code null} if not found.
	 */
	public T searchById(Class<T> classType, Long id);

	/**
	 * Retrieves all entities of a given type.
	 *
	 * @param classType The class of the entity.
	 * @return A list of all found entities, or an empty list if none exist.
	 */
	public List<T> searchAll(Class<T> classType);

	/**
	 * Updates an existing entity in the database.
	 *
	 * @param entityWithNewInformation The entity with updated values.
	 * @return The updated managed entity.
	 */
	public T update(T entityWithNewInformation);

	/**
	 * Deletes a given entity from the database.
	 *
	 * @param entity The entity to delete.
	 */
	public void delete(T entity);
}
