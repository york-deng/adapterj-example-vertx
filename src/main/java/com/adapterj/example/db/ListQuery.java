package com.adapterj.example.db;

import java.util.List;

public interface ListQuery<T> extends Query<T> {

	List<T> findAllItems() throws QueryException;

	int getCount() throws QueryException;

}
