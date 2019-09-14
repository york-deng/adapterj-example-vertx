package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.adapterj.example.pojo.Source;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class SourceListQuery implements ListQuery<Source> {
    
    private static final boolean DEBUG = Debugger.DEBUG;
    
    private static final String TAG = SourceListQuery.class.getName();

    private static SourceListQuery instance = new SourceListQuery();

    private OrmOpenHelper _helper = null;

    /**
     * Basic Constructor.
     */
    public SourceListQuery() {
        try {
            _helper = new OrmOpenHelper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Constructors.
     */
    public SourceListQuery(OrmOpenHelper helper) {
        _helper = helper;
    }
    
    /**
     * 
     * @return
     */
    public static SourceListQuery getInstance() {
        return (instance);
    }
    
    /**
     * 
     * @return
     */
    public static SourceListQuery getInstance(OrmOpenHelper helper) {
        return (instance = new SourceListQuery(helper));
    }
    
    // ListQuery methods
    
    @Override
    public List<Source> findAllItems() {
        List<Source> returnObjects = null;
        try {
            final Dao<Source, Long> dao = _helper.getDao(Source.class);
            final QueryBuilder<Source, Long> builder = dao.queryBuilder();
            builder.orderBy(Source.COLUMN_ID, true);
            
            final PreparedQuery<Source> query = builder.prepare();
            
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
