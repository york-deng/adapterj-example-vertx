package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import com.adapterj.example.pojo.Source;
import com.adapterj.example.pojo.Version1;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

public class VersionQuery1 implements POJOQuery<Version1> {
	
	private static final boolean DEBUG = Debugger.DEBUG;
	
	private static final String TAG = VersionQuery1.class.getName();

    private static VersionQuery1 instance = new VersionQuery1();

	private OrmOpenHelper _helper = null;

    /**
     * Basic Constructor.
     */
    public VersionQuery1() {
    	try {
			_helper = new OrmOpenHelper();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Constructors.
     */
    public VersionQuery1(final OrmOpenHelper helper) {
    	_helper = helper;
    }
    
    /**
     * 
     * @return
     */
    public static VersionQuery1 getInstance() {
    	return (instance);
    }
    
    /**
     * 
     * @return
     */
    public static VersionQuery1 getInstance(final OrmOpenHelper helper) {
    	return (instance = new VersionQuery1(helper));
    }
  
    /**
     * 
     * @return
     */
    public Version1 findLatest() {
		Version1 pojo = null;
		try {
	        final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
	        final QueryBuilder<Version1, Long> builder = dao.queryBuilder();
	
	        // WHERE
	        builder.where().gt(Version1.COLUMN_STATE, -1);

	        // ORDER BY
	        final StringBuffer orderBy = new StringBuffer();
	        orderBy.append(Version1.COLUMN_YEAR).append(' ').append(ORDER_BY_DESC).append(',').append(' ');
	        orderBy.append(Version1.COLUMN_SERIAL).append(' ').append(ORDER_BY_DESC);
			builder.orderByRaw(orderBy.toString());
			
	        final PreparedQuery<Version1> query = builder.prepare();
	        String sql = query.getStatement();
	        if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String f = "(%s:%d) %s: sql is \"%s\"";
	            Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), sql));
	        }
	        pojo = dao.queryForFirst(query);
		} catch (SQLException thrown) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: SQLException: ";
			final String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            if (DEBUG) Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		} 
		return (pojo);
    }
    
    /**
     * 
     * @param source
     * @param type
     * @return
     */
	public Version1 findLatestBySourceAndType(final Long source, final Integer type) {
		if (source == null) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: IllegalArgumentException: source is null";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new QueryException(error);
		}
		if (type == null) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: IllegalArgumentException: type is null";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new QueryException(error);
		}
		Version1 version = null;
		try {
	        final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
	        final QueryBuilder<Version1, Long> builder = dao.queryBuilder();
	
	        // WHERE
	        builder.where().eq(Version1.COLUMN_ID_SOURCE, source).and()
	        			   .eq(Version1.COLUMN_TYPE, type).and()
	        			   .gt(Version1.COLUMN_STATE, -1);

	        // ORDER BY
	        final StringBuffer orderBy = new StringBuffer();
	        orderBy.append(Version1.COLUMN_YEAR).append(' ').append(ORDER_BY_DESC).append(',').append(' ');
	        orderBy.append(Version1.COLUMN_SERIAL).append(' ').append(ORDER_BY_DESC);
			builder.orderByRaw(orderBy.toString());
			
	        final PreparedQuery<Version1> query = builder.prepare();
	        String sql = query.getStatement();
	        if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String f = "(%s:%d) %s: sql is \"%s\"";
	            Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), sql));
	        }
	        version = dao.queryForFirst(query);
		} catch (SQLException thrown) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: SQLException: ";
			final String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            if (DEBUG) Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		} 
		return (version);
	}
	
	/**
	 * 
	 * @param source
	 * @param type
	 * @return
	 */
	public Version1 findLatestBySourceAndType(final Source source, final Integer type) {
		if (source == null) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: IllegalArgumentException: source is null";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new QueryException(error);
		}
		final Long id = source.getId();
		if (id == null) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: IllegalArgumentException: source is %s";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), source.toJSONString());
			throw new QueryException(error);
		}
		if (type == null) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: IllegalArgumentException: type is null";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new QueryException(error);
		}
		return findLatestBySourceAndType(id, type);
	}

	public Version1 findFirstByAttr(final String attrName, final String attrValue) {
		Version1 version = null;
		try {
			final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
			final QueryBuilder<Version1, Long> builder = dao.queryBuilder();
			builder.where().eq(attrName, attrValue);
			final PreparedQuery<Version1> query = builder.prepare();
			version = dao.queryForFirst(query);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return (version);
	}

	// POJOQuery methods

	@Override
	public boolean exists(final Version1 version) throws QueryException {
		try {
			final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
			
			// 未实现。
			return dao.countOf() > 0;
		} catch (SQLException e) {
			throw new QueryException(e);
		}
	}

	@Override
	public Version1 findById(final Long id) {
		Version1 pojo = null;
		try {
			final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
			pojo = dao.queryForId(id);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return (pojo);
	}

	@Override
	public Version1 refresh(Version1 pojo) throws QueryException {
		try {
			final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
			dao.refresh(pojo);
		} catch (SQLException thrown) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: pojo is %s";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), pojo.toString());
            Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		}
		return (pojo);
	}

	@Override
	public Version1 createOrUpdate(Version1 pojo) throws QueryException {
		try {
			final Date now = new Date();
			final Dao<Version1, Long> dao = _helper.getDao(Version1.class);
			pojo.setVersion("v2019_001");
			pojo.setInsertTime(now);
			pojo.setUpdateTime(now);
			pojo.setState(0);
			dao.createOrUpdate(pojo);
			dao.refresh(pojo);
		} catch (SQLException thrown) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: pojo is %s";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), pojo.toString());
            Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		}
		return (pojo);
	}

	@Override
	public int delete(final Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(final Version1 pojo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
