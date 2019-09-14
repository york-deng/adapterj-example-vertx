package com.adapterj.example.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.Callable;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.adapterj.example.pojo.Source1;
import com.adapterj.example.pojo.Version1;
import com.adapterj.exceptions.NotImplementedException;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

public class SourceQuery1 implements POJOQuery<Source1> {

	private static final boolean DEBUG = Debugger.DEBUG;
	
	private static final String TAG = SourceQuery1.class.getName();

	private static SourceQuery1 instance = new SourceQuery1();
	
	private OrmOpenHelper _helper = null;
	
	/**
	 * 
	 * Constructors
	 */
	public SourceQuery1() {
		try {
			_helper = new OrmOpenHelper();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Constructors
	 */
	public SourceQuery1(final OrmOpenHelper helper) {
		_helper = helper;
	}
	
	/**
	 * 
	 * @return
	 */
	public static SourceQuery1 getInstance() {
		return (instance);
	}
	
	/**
	 * 
	 * @return
	 */
	public static SourceQuery1 getInstance(final OrmOpenHelper helper) {
		return (instance = new SourceQuery1(helper));
	}

	public Source1 findFirstByAttr(final String attrName, final String attrValue) {
		if (attrName == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: attrName is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		if (attrValue == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: attrValue is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		Source1 returnObj = null;
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			final QueryBuilder<Source1, Long> builder = dao.queryBuilder();
			builder.where().eq(attrName, attrValue);
			final PreparedQuery<Source1> query = builder.prepare();
			returnObj = dao.queryForFirst(query);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return returnObj;
	}

	public Source1 findByMatching(final Source1 parameter) {
		if (parameter == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: parameter is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		Source1 returnObj = null;
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			final QueryBuilder<Source1, Long> builder = dao.queryBuilder();

			final Long parameterId = parameter.getId();
			final String domain = parameter.getDomain();
			final String www = parameter.getWWW();
			
			final Where<Source1, Long> where = builder.where().isNotNull(Source1.COLUMN_ID);
			if (parameterId != null && parameterId > 0) where.and().eq(Source1.COLUMN_ID, parameterId);
			if ((parameterId == null || parameterId <= 0) && domain != null) where.and().eq(Source1.COLUMN_DOMAIN, domain);
			if ((parameterId == null || parameterId <= 0) && www != null) where.and().eq(Source1.COLUMN_WWW, www);

			final PreparedQuery<Source1> query = builder.prepare();
			final String sql = query.getStatement();
			if (DEBUG) {
				StackTraceElement t = (new Throwable()).getStackTrace()[0];
				String format = "(%s:%d) %s: sql is \"%s\"";
				Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), sql));
			}
			returnObj = dao.queryForFirst(query);
		} catch (SQLException e) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: SQLException: parameter is %s";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter.toString());
			Log.i(TAG, g, e);
			throw new QueryException(g, e);
		}
		return (returnObj);
	}

	/**
	 * 
	 * @param source
	 * @param version
	 * @return
	 * @throws QueryException
	 */
	public Source1 create(final Source1 source, final Version1 version) throws QueryException {
		if (source == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: source is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		if (version == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: version is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		final TransactionManager transactionManager = new TransactionManager(_helper.getConnectionSource());
		final Callable<Source1> callable = new Callable<Source1>() {
			@Override
			public Source1 call() throws SQLException { // Make sure that if either one fails, the transaction is rolled back
				final Date now = new Date();
				source.setVersion(version.getVersion());
				source.setInsertTime(now);
				source.setUpdateTime(now);
				source.setState(Source1.STATE_SUBSCRIBED);
				
				final Dao<Source1, Long> dao1 = _helper.getDao(Source1.class);
				dao1.create(source);
				dao1.refresh(source);
				
				final Version1 newVersion = new Version1();
				newVersion.setSource(source);
				newVersion.setType(Version1.TYPE_SOURCE);
				newVersion.setVersion(version.getVersion());
				newVersion.setYear(version.getYear());
				newVersion.setSerial(version.getSerial());
				newVersion.setInsertTime(now);
				newVersion.setUpdateTime(now);
				newVersion.setState(0);
				
				final Dao<Version1, Long> dao2 = _helper.getDao(Version1.class);
				dao2.create(newVersion);
				
				return (source);
			}
		};
		Source1 returnObj = null;
		try {
			returnObj = transactionManager.callInTransaction(callable);
		} catch (SQLException thrown) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			String f = "(%s:%d) %s: SQLException: source is %s";
			String error = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), source.toString());
			Log.i(TAG, error, thrown);
			throw new QueryException(error, thrown);
		}
		return (returnObj);
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 * @throws QueryException
	 */
	public Source1 update(final Source1 parameter) throws QueryException {
		if (parameter == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: id is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		// .name
		final String name = parameter.getName();
		if (name == null) {
			final String error = String.format("parameter is %s: name is null", parameter);
			throw new IllegalArgumentException(error);
		}
		if (name.isEmpty()) {
			final String error = String.format("parameter is %s: name is empty", parameter);
			throw new IllegalArgumentException(error);
		}
		// .abbr
		final String abbr = parameter.getAbbr();
		if (abbr == null) {
			final String error = String.format("parameter is %s: abbr is null", parameter);
			throw new IllegalArgumentException(error);
		}
		if (abbr.isEmpty()) {
			final String error = String.format("parameter is %s: abbr is empty", parameter);
			throw new IllegalArgumentException(error);
		}
		// .logo
		final String logo = parameter.getLogo();
		// .domain
		final String domain = parameter.getDomain();
		if (domain == null) {
			final String error = String.format("parameter is %s: domain is null", parameter);
			throw new IllegalArgumentException(error);
		}
		if (domain.isEmpty()) {
			final String error = String.format("parameter is %s: domain is empty", parameter);
			throw new IllegalArgumentException(error);
		}
		// .www
		final String www = parameter.getWWW();
		if (www == null) {
			final String error = String.format("parameter is %s: www is null", parameter);
			throw new IllegalArgumentException(error);
		}
		if (www.isEmpty()) {
			final String error = String.format("parameter is %s: www is empty", parameter);
			throw new IllegalArgumentException(error);
		}
		// .description
		final String description = parameter.getDescription();
		// .type
		final Integer type = parameter.getType();
		// .country
		final String country = parameter.getCountry();
		// .language
		final String language = parameter.getLanguage();
		// .specific
		final String specific = parameter.getSpecific();
		// .priority
		final Integer priority = parameter.getPriority();
		if (priority == null) {
			final String error = String.format("parameter is %s: priority is null", parameter);
			throw new IllegalArgumentException(error);
		}
		// .latency
		final Integer latency = parameter.getLatency();
		if (latency == null) {
			final String error = String.format("parameter is %s: latency is null", parameter);
			throw new IllegalArgumentException(error);
		}
		// .label1
		final String label1 = parameter.getLabel1();
		// .url1
		final String url1 = parameter.getURL1();
		// .limit1
		final Integer limit1 = parameter.getLimit1();
		// .type1
		final Integer type1 = parameter.getType1();
		// .encoding1
		final String encoding1 = parameter.getEncoding1();
		// .label2
		final String label2 = parameter.getLabel2();
		// .url2
		final String url2 = parameter.getURL2();
		// .limit2
		final Integer limit2 = parameter.getLimit2();
		// .type2
		final Integer type2 = parameter.getType2();
		// .encoding2
		final String encoding2 = parameter.getEncoding2();
		// .label3
		final String label3 = parameter.getLabel3();
		// .url3
		final String url3 = parameter.getURL3();
		// .limit2
		final Integer limit3 = parameter.getLimit3();
		// .type3
		final Integer type3 = parameter.getType3();
		// .encoding3
		final String encoding3 = parameter.getEncoding3();
		// .label4
		final String label4 = parameter.getLabel4();
		// .url4
		final String url4 = parameter.getURL4();
		// .limit4
		final Integer limit4 = parameter.getLimit4();
		// .type4
		final Integer type4 = parameter.getType4();
		// .encoding4
		final String encoding4 = parameter.getEncoding4();
		// .label5
		final String label5 = parameter.getLabel5();
		// .url5
		final String url5 = parameter.getURL5();
		// .limit5
		final Integer limit5 = parameter.getLimit5();
		// .type5
		final Integer type5 = parameter.getType5();
		// .encoding5
		final String encoding5 = parameter.getEncoding5();
		// .label6
		final String label6 = parameter.getLabel6();
		// .url6
		final String url6 = parameter.getURL6();
		// .limit6
		final Integer limit6 = parameter.getLimit6();
		// .type6
		final Integer type6 = parameter.getType6();
		// .encoding6
		final String encoding6 = parameter.getEncoding6();
		// .label7
		final String label7 = parameter.getLabel7();
		// .url7
		final String url7 = parameter.getURL7();
		// .limit7
		final Integer limit7 = parameter.getLimit7();
		// .type7
		final Integer type7 = parameter.getType7();
		// .encoding7
		final String encoding7 = parameter.getEncoding7();
		// .label8
		final String label8 = parameter.getLabel8();
		// .url8
		final String url8 = parameter.getURL8();
		// .limit8
		final Integer limit8 = parameter.getLimit8();
		// .type8
		final Integer type8 = parameter.getType8();
		// .encoding8
		final String encoding8 = parameter.getEncoding8();
		// .label9
		final String label9 = parameter.getLabel9();
		// .url9
		final String url9 = parameter.getURL9();
		// .limit9
		final Integer limit9 = parameter.getLimit9();
		// .type9
		final Integer type9 = parameter.getType9();
		// .encoding9
		final String encoding9 = parameter.getEncoding9();
		
		Source1 returnObject = null;
		try {
			final Long id = parameter.getId();

			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			final QueryBuilder<Source1, Long> builder = dao.queryBuilder();
			final Where<Source1, Long> where = builder.where();
			if (id != null) where.eq(Source1.COLUMN_ID, id);
			else where.eq(Source1.COLUMN_DOMAIN, domain).or()
				      .eq(Source1.COLUMN_WWW, www);
			
			final PreparedQuery<Source1> query =  builder.prepare();
			returnObject = dao.queryForFirst(query);

			returnObject.setName(name); // required
			returnObject.setAbbr(abbr); // required
			returnObject.setLogo(logo == null || logo.isEmpty() ? null : logo);
			returnObject.setDomain(domain); // required
			returnObject.setWWW(www); // required
			returnObject.setDescription(description);
			returnObject.setType(type);  // required
			returnObject.setCountry(country == null || country.isEmpty() ? null : country);
			returnObject.setLanguage(language == null || language.isEmpty() ? null : language);
			returnObject.setSpecific(specific);
			returnObject.setPriority(priority); // required
			returnObject.setLatency(latency); // required
			
			returnObject.setLabel1(label1);
			returnObject.setURL1(url1 == null || url1.isEmpty() ? null : url1);
			returnObject.setLimit1(limit1 == null ? 0 : limit1);
			returnObject.setType1(type1);
			returnObject.setEncoding1(encoding1);

			returnObject.setLabel2(label2);
			returnObject.setURL2(url2 == null || url2.isEmpty() ? null : url2);
			returnObject.setLimit2(limit2 == null ? 0 : limit2);
			returnObject.setType2(type2);
			returnObject.setEncoding2(encoding2 == null || encoding2.isEmpty() ? null : encoding2);
			
			returnObject.setLabel3(label3);
			returnObject.setURL3(url3 == null || url3.isEmpty() ? null : url3);
			returnObject.setLimit3(limit3 == null ? 0 : limit3);
			returnObject.setType3(type3);
			returnObject.setEncoding3(encoding3 == null || encoding3.isEmpty() ? null : encoding3);
			
			returnObject.setLabel4(label4);
			returnObject.setURL4(url4 == null || url4.isEmpty() ? null : url4);
			returnObject.setLimit4(limit4 == null ? 0 : limit4);
			returnObject.setType4(type4);
			returnObject.setEncoding4(encoding4 == null || encoding4.isEmpty() ? null : encoding4);
			
			returnObject.setLabel5(label5);
			returnObject.setURL5(url5 == null || url5.isEmpty() ? null : url5);
			returnObject.setLimit5(limit5 == null ? 0 : limit1);
			returnObject.setType5(type5);
			returnObject.setEncoding5(encoding5 == null || encoding5.isEmpty() ? null : encoding5);
			
			returnObject.setLabel6(label6);
			returnObject.setURL6(url6 == null || url6.isEmpty() ? null : url6);
			returnObject.setLimit6(limit6 == null ? 0 : limit6);
			returnObject.setType6(type6);
			returnObject.setEncoding6(encoding6 == null || encoding6.isEmpty() ? null : encoding6);
			
			returnObject.setLabel7(label7);
			returnObject.setURL7(url7 == null || url7.isEmpty() ? null : url7);
			returnObject.setLimit7(limit7 == null ? 0 : limit7);
			returnObject.setType7(type7);
			returnObject.setEncoding7(encoding7 == null || encoding7.isEmpty() ? null : encoding7);
			
			returnObject.setLabel8(label8);
			returnObject.setURL8(url8 == null || url8.isEmpty() ? null : url8);
			returnObject.setLimit8(limit8 == null ? 0 : limit8);
			returnObject.setType8(type8);
			returnObject.setEncoding8(encoding8 == null || encoding8.isEmpty() ? null : encoding8);
			
			returnObject.setLabel9(label9);
			returnObject.setURL9(url9 == null || url9.isEmpty() ? null : url9);
			returnObject.setLimit9(limit9 == null ? 0 : limit9);
			returnObject.setType9(type9);
			returnObject.setEncoding9(encoding9 == null || encoding9.isEmpty() ? null : encoding9);
			
			returnObject.setUpdateTime(new Date());

			dao.update(returnObject);
			dao.refresh(returnObject);
		} catch (SQLException e) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: SQLException: parameter is %s";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter.toString());
			Log.i(TAG, g, e);
			throw new QueryException(g, e);
		}
		return (returnObject);
	}

	// POJOQuery methods

	@Override
	public boolean exists(final Source1 parameter) throws QueryException {
		if (parameter == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: parameter is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		
		final String name = parameter.getName();
		final String domain = parameter.getDomain();
		final String www = parameter.getWWW();
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			final QueryBuilder<Source1, Long> builder = dao.queryBuilder();
			if (name != null) builder.where().eq(Source1.COLUMN_NAME, name);
			else if (domain != null) builder.where().eq(Source1.COLUMN_DOMAIN, domain);
			else if (www != null) builder.where().eq(Source1.COLUMN_WWW, www);
			else return (false);
			
			builder.setCountOf(true);
			final PreparedQuery<Source1> query =  builder.prepare();
			final Long count = dao.countOf(query);
			if (DEBUG) {
				final StackTraceElement t = (new Throwable()).getStackTrace()[0];
				final String f = "(%s:%d) %s: count is %d";
				final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), count);
				Log.i(TAG, g);
			}
			return (count > 0);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
	}

	@Override
	public Source1 findById(final Long id) {
		if (id == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: id is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		Source1 returnObj = null;
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			returnObj = dao.queryForId(id);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return (returnObj);
	}

	@Override
	public Source1 refresh(Source1 parameter) throws QueryException {
		if (parameter == null) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: parameter is null";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName());
			throw new IllegalArgumentException(g);
		}
		try {
			final Dao<Source1, Long> dao = _helper.getDao(Source1.class);
			dao.refresh(parameter);
		} catch (SQLException e) {
			final StackTraceElement t = (new Throwable()).getStackTrace()[0];
			final String f = "(%s:%d) %s: SQLException: parameter is %s";
			final String g = String.format(f, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter.toString());
			Log.i(TAG, g, e);
			throw new QueryException(g, e);
		}
		return (parameter);
	}

	@Override
	public Source1 createOrUpdate(final Source1 pojo) throws QueryException {
		final String s = "You can NOT call createOrUpdate(Source1) method to INSERT or UPDATE a source record, " +
				         "but you CAN call create(Source1, Version) method to INSERT a new source record, or "   + 
				         "call update(Source1) method to UPDATE a source record. ";
		throw new NotImplementedException(s);
	}
	
	@Override
	public int delete(final Long id) {
		throw new NotImplementedException("...");
	}

	@Override
	public int delete(final Source1 pojo) {
		throw new NotImplementedException("...");
	}
}
