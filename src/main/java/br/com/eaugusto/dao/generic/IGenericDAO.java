package br.com.eaugusto.dao.generic;

import java.util.List;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @param <T>
 * @since July 15, 2025
 */
public interface IGenericDAO<T> {

	public T register(T entity);

	public T searchById(Class<T> classType, Long id);

	public List<T> searchAll(Class<T> classType);

	public void delete(T entity);
}
