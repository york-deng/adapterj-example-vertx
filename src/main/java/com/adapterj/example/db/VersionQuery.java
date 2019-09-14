package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;

import com.adapterj.example.pojo.Source;
import com.adapterj.example.pojo.Version;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class VersionQuery implements POJOQuery<Version> {
	
	private static final boolean DEBUG = Debugger.DEBUG;
	
	private static final String TAG = VersionQuery.class.getName();

    private static VersionQuery instance = new VersionQuery();

	private OrmOpenHelper _helper = null;

    /**
     * Basic Constructor.
     */
    public VersionQuery() {
    	try {
			_helper = new OrmOpenHelper();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Constructors.
     */
    public VersionQuery(final OrmOpenHelper helper) {
    	_helper = helper;
    }
    
    /**
     * 
     * @return
     */
    public static VersionQuery getInstance() {
    	return (instance);
    }
    
    /**
     * 
     * @return
     */
    public static VersionQuery getInstance(final OrmOpenHelper helper) {
    	return (instance = new VersionQuery(helper));
    }
  
    /**
     * 
     * @return
     */
    public Version findLatest() {
		Version pojo = null;
		try {
	        final Dao<Version, Long> dao = _helper.getDao(Version.class);
	        final QueryBuilder<Version, Long> builder = dao.queryBuilder();
	
	        // WHERE
	        builder.where().gt(Version.COLUMN_STATE, -1);

	        // ORDER BY
	        final StringBuffer orderBy = new StringBuffer();
	        orderBy.append(Version.COLUMN_YEAR).append(' ').append(ORDER_BY_DESC).append(',').append(' ');
	        orderBy.append(Version.COLUMN_SERIAL).append(' ').append(ORDER_BY_DESC);
			builder.orderByRaw(orderBy.toString());
			
	        final PreparedQuery<Version> query = builder.prepare();
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
	public Version findLatestBySourceAndType(final Long source, final Integer type) {
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
		Version version = null;
		try {
	        final Dao<Version, Long> dao = _helper.getDao(Version.class);
	        final QueryBuilder<Version, Long> builder = dao.queryBuilder();
	
	        // WHERE
	        builder.where().eq(Version.COLUMN_ID_SOURCE, source).and()
	        			   .eq(Version.COLUMN_TYPE, type).and()
	        			   .gt(Version.COLUMN_STATE, -1);

	        // ORDER BY
	        final StringBuffer orderBy = new StringBuffer();
	        orderBy.append(Version.COLUMN_YEAR).append(' ').append(ORDER_BY_DESC).append(',').append(' ');
	        orderBy.append(Version.COLUMN_SERIAL).append(' ').append(ORDER_BY_DESC);
			builder.orderByRaw(orderBy.toString());
			
	        final PreparedQuery<Version> query = builder.prepare();
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
	public Version findLatestBySourceAndType(final Source source, final Integer type) {
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

	public Version findFirstByAttr(final String attrName, final String attrValue) {
		Version version = null;
		try {
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
			final QueryBuilder<Version, Long> builder = dao.queryBuilder();
			builder.where().eq(attrName, attrValue);
			final PreparedQuery<Version> query = builder.prepare();
			version = dao.queryForFirst(query);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return (version);
	}

	// POJOQuery methods

	@Override
	public boolean exists(final Version version) throws QueryException {
		try {
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
			
			// 未实现。
			return dao.countOf() > 0;
		} catch (SQLException e) {
			throw new QueryException(e);
		}
	}

	@Override
	public Version findById(final Long id) {
		Version pojo = null;
		try {
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
			pojo = dao.queryForId(id);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return (pojo);
	}

	@Override
	public Version refresh(Version pojo) throws QueryException {
		try {
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
			dao.refresh(pojo);
		} catch (SQLException thrown) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: pojo is %s";
            String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), pojo.toJSONString());
            Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		}
		return (pojo);
	}

	@Override
	public Version createOrUpdate(Version pojo) throws QueryException {
		try {
			final Date now = new Date();
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
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
	public int delete(final Version pojo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
