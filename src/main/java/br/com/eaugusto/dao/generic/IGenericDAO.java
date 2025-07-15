package br.com.eaugusto.dao.generic;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @param <T>
 * @since July 15, 2025
 */
public interface IGenericDAO<T> {

	public T register(T entity);
}
