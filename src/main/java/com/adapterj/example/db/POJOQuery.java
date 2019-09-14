package com.adapterj.example.db;

public interface POJOQuery<T> extends Query<T> {

	boolean exists(final T o) throws QueryException;
	
	T findById(final Long id) throws QueryException;

	T refresh(final T o) throws QueryException;

	T createOrUpdate(final T o) throws QueryException;

	int delete(final Long id) throws QueryException;

	int delete(final T o) throws QueryException;

}
