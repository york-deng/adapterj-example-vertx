package com.adapterj.example.db;

import java.sql.SQLException;

public class QueryException extends RuntimeException {

	private static final long serialVersionUID = 7564646411574810724L;

	/**
	 * 
	 * @param error
	 */
	public QueryException(String error) {
		super(error);
	}
	
	/**
	 * 
	 * @param error
	 * @param thrown
	 */
	public QueryException(String error, NullPointerException thrown) {
		super(error, thrown);
	}
	
	/**
	 * 
	 * @param thrown
	 */
	public QueryException(NullPointerException thrown) {
		super(thrown);
	}
	
	/**
	 * 
	 * @param error
	 * @param thrown
	 */
	public QueryException(String error, IllegalArgumentException thrown) {
		super(error, thrown);
	}

	/**
	 * 
	 * @param thrown
	 */
	public QueryException(IllegalArgumentException thrown) {
		super(thrown);
	}

	/**
	 * 
	 * @param error
	 * @param thrown
	 */
	public QueryException(String error, SQLException thrown) {
		super(error, thrown);
	}
	
	/**
	 * 
	 * @param thrown
	 */
	public QueryException(SQLException thrown) {
		super(thrown);
	}
}
