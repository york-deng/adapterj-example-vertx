package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.adapterj.example.pojo.Source1;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class SourceListQuery1 implements ListQuery<Source1> {
	
	private static final boolean DEBUG = Debugger.DEBUG;
	
    private static final String TAG = SourceListQuery1.class.getName();

    private static SourceListQuery1 instance = new SourceListQuery1();

	private OrmOpenHelper _helper = null;

    /**
     * Basic Constructor.
     */
    public SourceListQuery1() {
    	try {
			_helper = new OrmOpenHelper();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Constructors.
     */
    public SourceListQuery1(OrmOpenHelper helper) {
    	_helper = helper;
    }
    
    /**
     * 
     * @return
     */
    public static SourceListQuery1 getInstance() {
    	return (instance);
    }
    
    /**
     * 
     * @return
     */
    public static SourceListQuery1 getInstance(OrmOpenHelper helper) {
    	return (instance = new SourceListQuery1(helper));
    }
    
    // ListQuery methods
    
	@Override
	public List<Source1> findAllItems() {
		List<Source1> returnObjects = null;
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			final QueryBuilder<Source1, Long> builder = dao.queryBuilder();
			builder.orderBy(Source1.COLUMN_ID, true);
			
            final PreparedQuery<Source1> query = builder.prepare();
            
			final Date begin = new Date();
			returnObjects = dao.query(query);
			final Date end = new Date();
			final long cost = end.getTime() - begin.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: query: cost is %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost));
			}
		} catch (SQLException e) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: ";
            String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            Log.e(TAG, g, e);
		}
		return (returnObjects);
	}

	@Override
	public int getCount() throws QueryException {
		return 0;
	}
}
