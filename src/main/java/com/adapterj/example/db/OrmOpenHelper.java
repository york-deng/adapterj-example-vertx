package com.adapterj.example.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcDatabaseConnection;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.adapterj.example.pojo.Source;
import com.adapterj.example.pojo.Source1;
import com.adapterj.example.pojo.Version;
import com.adapterj.example.pojo.Version1;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

/**
 * MySQL URL format: jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>
 * 
 * @author York/GuangYu DENG
 */
public class OrmOpenHelper {

    private static final boolean DEBUG = Debugger.DEBUG;

    private static final String TAG = OrmOpenHelper.class.getName();

    private static final Properties properties = new Properties();
    
    static {
        try {
            properties.load(OrmOpenHelper.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static final String HOST = properties.getProperty("jdbc.host");
    
    private static final String PORT = properties.getProperty("jdbc.port");
    
    private static final String DATABASE_NAME = properties.getProperty("jdbc.db.name");
    
    private static final String DATABASE_USER = properties.getProperty("jdbc.db.user");
    
    private static final String DATABASE_PASSWORD = properties.getProperty("jdbc.db.password");
    
    private static final String JDBC_H2_CONNECTION_URL_PREFIX = "jdbc:h2:mem:";
    
    private static final String JDBC_H2_CONNECTION_URL = JDBC_H2_CONNECTION_URL_PREFIX + DATABASE_NAME;
    
    private static final String JDBC_MYSQL_CONNECTION_URL_PREFIX = "jdbc:mysql:";

    private static final String JDBC_MYSQL_CONNECTION_URL = JDBC_MYSQL_CONNECTION_URL_PREFIX + "//" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&user=" + DATABASE_USER + "&password=" + DATABASE_PASSWORD + "&autoReconnect=true";
    
    static {
        if (DEBUG){
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: jdbc URL is \"%s\"";
            String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), JDBC_H2_CONNECTION_URL);
            Log.i(TAG, g);
        }
    }
    
    private static String _jdbcUrl = JDBC_H2_CONNECTION_URL;

    private static int newVer = 8;

    private final ConnectionSource _context;

    /**
     * 
     * @throws SQLException
     */
    public OrmOpenHelper() throws SQLException {
        this(_jdbcUrl = JDBC_H2_CONNECTION_URL); // JDBC_MYSQL_CONNECTION_URL
    }
    
    /**
     * 
     * @param jdbcUrl
     * @throws SQLException
     */
    public OrmOpenHelper(String jdbcUrl) throws SQLException {
        this(new JdbcConnectionSource(_jdbcUrl = jdbcUrl));
    }
    
    /**
     *
     * @param context
     * @throws SQLException 
     */
    protected OrmOpenHelper(ConnectionSource context) throws SQLException {
        _context = context;
        final int nowVer = schema();
        if (nowVer == 0 || _jdbcUrl.startsWith(JDBC_H2_CONNECTION_URL_PREFIX)) {
            onCreate(_context);
        } else if (newVer > 1) {
            onUpgrade(_context, nowVer, newVer);
        }
    }

    public final ConnectionSource getConnectionSource() {
        return _context;
    }

    public final Connection getConnection() throws SQLException {
        return ((JdbcDatabaseConnection) _context.getReadWriteConnection()).getInternalConnection();
    }
    
    public boolean isAndroid() { return false; }
    
    // Like OrmLiteSqliteOpenHelper methods

    public void onCreate(ConnectionSource conn) {
        // create tables
        try {
            TableUtils.createTable(conn, Version.class);
            TableUtils.createTable(conn, Source.class);
            TableUtils.createTable(conn, Version1.class);
            TableUtils.createTable(conn, Source1.class);
        } catch (SQLException e) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: ";
            String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            Log.e(TAG, g, e);
        }
        
        // init schema
        try {
            schema(conn); 
        } catch (SQLException e) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: ";
            String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            Log.e(TAG, g, e);
        }
        
        // init data
        try {
            init(); 
        } catch (SQLException e) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String f = "(%s:%d) %s: SQLException: ";
            String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
            Log.e(TAG, g, e);
        } catch (FileNotFoundException e) {
	    StackTraceElement t = (new Throwable()).getStackTrace()[0];
	    String f = "(%s:%d) %s: FileNotFoundException: ";
	    String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
	    Log.e(TAG, g, e);
	}
    }

    public void onUpgrade(ConnectionSource conn, int oldVer, int newVer) {
        if (newVer > oldVer) {
            final int nowVer = schema();
            if (newVer > nowVer) {
                try {
                    // drop tables
                    TableUtils.dropTable(conn, Source1.class, true);
                    TableUtils.dropTable(conn, Version1.class, true);
                    TableUtils.dropTable(conn, Source.class, true);
                    TableUtils.dropTable(conn, Version.class, true);

                    // create tables
                    TableUtils.createTable(conn, Version.class);
                    TableUtils.createTable(conn, Source.class);
                    TableUtils.createTable(conn, Version1.class);
                    TableUtils.createTable(conn, Source1.class);
                } catch (SQLException e) {
                    StackTraceElement t = (new Throwable()).getStackTrace()[0];
                    String f = "(%s:%d) %s: SQLException: ";
                    String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
                    Log.e(TAG, error, e);
                }

                // update schema
                try {
                    schema(oldVer, newVer); 
                } catch (SQLException e) {
                    StackTraceElement t = (new Throwable()).getStackTrace()[0];
                    String f = "(%s:%d) %s: SQLException: ";
                    String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
                    Log.e(TAG, g, e);
                }

                // init data
                try {
                    init();
                } catch (SQLException e) {
                    StackTraceElement t = (new Throwable()).getStackTrace()[0];
                    String f = "(%s:%d) %s: SQLException: ";
                    String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
                    Log.e(TAG, g, e);
                } catch (FileNotFoundException e) {
                    StackTraceElement t = (new Throwable()).getStackTrace()[0];
                    String f = "(%s:%d) %s: FileNotFoundException: ";
                    String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
                    Log.e(TAG, g, e);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private final Map<Class, Dao<?, ?>> map = new HashMap<Class, Dao<?, ?>>(); // ConcurrentHashMap

    @SuppressWarnings("unchecked")
    public <D extends Dao<T, ?>, T> D getDao(final Class<T> clazz) throws SQLException {
        if (Schema.class.equals(clazz)) {
            final boolean exist = map.containsKey(Schema.class);
            if (!exist) map.put(clazz, (D) DaoManager.createDao(_context, Schema.class));
            return (D) map.get(Schema.class);
        }
        if (Version.class.equals(clazz)) {
            final boolean exist = map.containsKey(Version.class);
            if (!exist) map.put(clazz, (D) DaoManager.createDao(_context, Version.class));
            return (D) map.get(Version.class);
        }
        if (Source.class.equals(clazz)) {
            final boolean exist = map.containsKey(Source.class);
            if (!exist) map.put(clazz, (D) DaoManager.createDao(_context, Source.class));
            return (D) map.get(Source.class);
        }
        if (Version1.class.equals(clazz)) {
            final boolean exist = map.containsKey(Version1.class);
            if (!exist) map.put(clazz, (D) DaoManager.createDao(_context, Version1.class));
            return (D) map.get(Version.class);
        }
        if (Source1.class.equals(clazz)) {
            final boolean exist = map.containsKey(Source1.class);
            if (!exist) map.put(clazz, (D) DaoManager.createDao(_context, Source1.class));
            return (D) map.get(Source1.class);
        }
        return (null);
    }

    // init schema
    protected final void schema(ConnectionSource conn) throws SQLException {
        TableUtils.createTable(conn, Schema.class);
        
        final Schema schema = new Schema();
        schema.setVersion(newVer = 1);
        schema.setInsertTime(new Date());
        schema.setUpdateTime(new Date());
        schema.setState(0);
        getDao(Schema.class).create(schema);
    }
    
    private static int nowVer = 0;

    // get schema version
    protected final int schema() {
        if (nowVer == 0) {
            try {
                final Dao<Schema, Long> dao = getDao(Schema.class);
                final QueryBuilder<Schema, Long> builder = dao.queryBuilder();
                final PreparedQuery<Schema> query = builder.prepare();
                final Schema now = dao.queryForFirst(query);
                if (now != null) {
                    nowVer = now.getVersion();
                    if (DEBUG) {
                        StackTraceElement t = (new Throwable()).getStackTrace()[0];
                        String f = "(%s:%d) %s: nowVer is %d";
                        Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), nowVer));
                    }
                } else {
                    final Schema schema = new Schema();
                    schema.setVersion(nowVer = 1);
                    schema.setInsertTime(new Date());
                    schema.setUpdateTime(new Date());
                    schema.setState(0);
                    dao.create(schema);
                }
            } catch (SQLException ignore) {
                // ignore
            }
        }
        return (nowVer);
    }
    
    // update schema
    protected final void schema(int oldVer, int newVer) throws SQLException {
        if (newVer > oldVer) {
            try {
                final Dao<Schema, Long> dao = getDao(Schema.class);
                final QueryBuilder<Schema, Long> builder = dao.queryBuilder();
                final PreparedQuery<Schema> query = builder.prepare();
                final Schema schema = dao.queryForFirst(query);
            if (schema != null) {
                schema.setVersion(nowVer = newVer);
                schema.setUpdateTime(new Date());
                dao.update(schema);
                if (DEBUG) {
                    StackTraceElement t = (new Throwable()).getStackTrace()[0];
                    String format = "(%s:%d) %s: newVer is %d";
                    Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), newVer));
                }
            }
            } catch (SQLException ignore) {
                // ignore
            }
        }
    }
    
    // init data
    protected final void init() throws SQLException, FileNotFoundException {
        if (nowVer == 0 || _jdbcUrl.startsWith(JDBC_H2_CONNECTION_URL_PREFIX)) {
	    final Connection conn = getConnection();
            if (DEBUG) {
                StackTraceElement t = (new Throwable()).getStackTrace()[0];
                String f = "(%s:%d) %s: conn is \"%s\"";
                Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), conn));
            }
	    
	    final String dir = OrmOpenHelper.class.getClassLoader().getResource("").getPath();
	    if (DEBUG) {
		StackTraceElement t = (new Throwable()).getStackTrace()[0];
		String format = "(%s:%d) %s: base dir is %s";
		Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), dir));
	    }
	    
	    final InputStream in = new FileInputStream(dir + "adapterj-example-h2.sql");
	    if (DEBUG) {
                StackTraceElement t = (new Throwable()).getStackTrace()[0];
                String f = "(%s:%d) %s: in is %s";
                Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), in));
            }
	    
	    final Reader reader = new InputStreamReader(new FileInputStream(dir + "adapterj-example-h2.sql"));
            if (DEBUG) {
                StackTraceElement t = (new Throwable()).getStackTrace()[0];
                String f = "(%s:%d) %s: reader is \"%s\"";
                Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), reader));
            }
	    
	    RunScript.execute(conn, reader);
        }
        
    }

    // For test only
    
    public static void main(String[] args) {
        Source item = null;
        try {
            final OrmOpenHelper helper = new OrmOpenHelper();
            final Dao<Source, Long> dao = helper.getDao(Source.class);
            
            final Long id = 3L;
            item = dao.queryForId(id);
	    
            item.setWWW("www.bbwc.cn");
            item.setURL9(null);
            item.setUpdateTime(new Date());
            item.setVersion("for test update");
            if (DEBUG) {
                StackTraceElement t = (new Throwable()).getStackTrace()[0];
                String f = "(%s:%d) %s: item is %s";
                Log.i(TAG, String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), item.toJSONString()));
            }
	    
            dao.update(item);
            dao.refresh(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
