package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.adapterj.example.pojo.Version;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class VersionListQuery implements ListQuery<Version> {
	
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = VersionListQuery.class.getName();

    private static VersionListQuery instance = new VersionListQuery();

	private OrmOpenHelper _helper = null;

    /**
     * Basic Constructor.
     */
    public VersionListQuery() {
    	try {
			_helper = new OrmOpenHelper();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Constructors.
     */
    public VersionListQuery(OrmOpenHelper helper) {
    	_helper = helper;
    }
    
    /**
     * 
     * @return
     */
    public static VersionListQuery getInstance() {
    	return (instance);
    }
    
    /**
     * 
     * @return
     */
    public static VersionListQuery getInstance(OrmOpenHelper helper) {
    	return (instance = new VersionListQuery(helper));
    }
    
    // ListQuery methods
    
	@Override
	public List<Version> findAllItems() {
		List<Version> returnList = null;
		try {
			final Dao<Version, Long> dao = _helper.getDao(Version.class);
			final QueryBuilder<Version, Long> builder = dao.queryBuilder();
			builder.orderBy(Version.COLUMN_ID, true);
			
            final PreparedQuery<Version> query = builder.prepare();
            
			final Date begin = new Date();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: sqlite query: begin is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), begin.toString()));
			}
			returnList = dao.query(query);
			final Date end = new Date();
			final long cost = end.getTime() - begin.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: sqlite query: end is %s, %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end.toString(), cost));
			}
			
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: all list: (%d)";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), returnList.size()));
			}
		} catch (SQLException e) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String format = "(%s:%d) %s: SQLException: ";
            Log.e(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName()), e);
		}
		return (returnList);
	}

	@Override
	public int getCount() throws QueryException {
		return 0;
	}
}
