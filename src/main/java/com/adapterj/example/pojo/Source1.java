package com.adapterj.example.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * SQL:
 *
 * create table t_source (
 * id                   INTEGER                        not null,
 * name                 VARCHAR(255)                   not null,
 * abbr                 VARCHAR(255)                   not null,
 * logo                 VARCHAR(255),
 * domain               VARCHAR(255)                   not null,
 * www                  VARCHAR(255)                   not null,
 * description          VARCHAR(511),
 * type                 INTEGER                        not null,
 * country              CHAR(2),
 * language             CHAR(5),
 * specific             VARCHAR(511),
 * priority             INTEGER                        not null,
 * latency              INTEGER                        not null default 300,
 * label1               VARCHAR(255),
 * url1                 VARCHAR(255),
 * limit1               INTEGER                        default 0,
 * type1                INTEGER,
 * encoding1            VARCHAR(31),
 * label2               VARCHAR(255),
 * url2                 VARCHAR(255),
 * limit2               INTEGER                        default 0,
 * type2                INTEGER,
 * encoding2            VARCHAR(31),
 * label3               VARCHAR(255),
 * url3                 VARCHAR(255),
 * limit3               INTEGER                        default 0,
 * type3                INTEGER,
 * encoding3            VARCHAR(31),
 * label4               VARCHAR(255),
 * url4                 VARCHAR(255),
 * limit4               INTEGER                        default 0,
 * type4                INTEGER,
 * encoding4            VARCHAR(31),
 * label5               VARCHAR(255),
 * url5                 VARCHAR(255),
 * limit5               INTEGER                        default 0,
 * type5                INTEGER,
 * encoding5            VARCHAR(31),
 * label6               VARCHAR(255),
 * url6                 VARCHAR(255),
 * limit6               INTEGER                        default 0,
 * type6                INTEGER,
 * encoding6            VARCHAR(31),
 * label7               VARCHAR(255),
 * url7                 VARCHAR(255),
 * limit7               INTEGER                        default 0,
 * type7                INTEGER,
 * encoding7            VARCHAR(31),
 * label8               VARCHAR(255),
 * url8                 VARCHAR(255),
 * limit8               INTEGER                        default 0,
 * type8                INTEGER,
 * encoding8            VARCHAR(31),
 * label9               VARCHAR(255),
 * url9                 VARCHAR(255),
 * limit9               INTEGER                        default 0,
 * type9                INTEGER,
 * encoding9            VARCHAR(31),
 * version              VARCHAR(31)                    not null,
 * insert_time          TIMESTAMP                      not null,
 * update_time          TIMESTAMP                      not null,
 * state                INTEGER                        not null,
 * primary key (id)
 * );
 *
 * create unique index x_source_name on t_source (
 *        name ASC
 * );
 *
 * create unique index x_source_abbr on t_source (
 *        abbr ASC
 * );
 *
 * create unique index x_source_domain on t_source (
 *        domain ASC
 * );
 *
 * create unique index x_source_www on t_source (
 *        www ASC
 * );
 *
 * create index x_source_state on t_source (
 *        state ASC
 * );
 * 
 * @author York/GuangYu DENG <york.deng@qq.com>
 * 
 */
@DatabaseTable(tableName = "t_source")
public class Source1 implements Serializable {

    private static final long serialVersionUID = -555782359639459538L;

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ABBR = "abbr";

    public static final String COLUMN_DOMAIN = "domain";

    public static final String COLUMN_WWW = "www";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_COUNTRY = "country";

    public static final String COLUMN_LANGUAGE = "language";

    public static final String COLUMN_PRIORITY = "priority";

    public static final String COLUMN_LATENCY = "latency";

    public static final String COLUMN_LABEL1 = "label1";

    public static final String COLUMN_URL1 = "url1";

    public static final String COLUMN_LIMIT1 = "limit1";

    public static final String COLUMN_TYPE1 = "type1";

    public static final String COLUMN_VERSION = "version";

    public static final String COLUMN_STATE = "state";

    public static final int TYPE_OFFICIAL_SITE = 0x00;

    public static final int TYPE_OFFICIAL_SITE_HOMEPAGE = 0x01;

    public static final int TYPE_OFFICIAL_SITE_LIST_PAGES = 0x02;

    public static final int TYPE_OFFICIAL_SITE_RSS = 0x08;

    public static final int TYPE_THIRD_PARTY_PLATFORM = 0x10;

    public static final int TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE = 0x11;

    public static final int TYPE_THIRD_PARTY_LIST_PAGES = 0x12;

    public static final int TYPE_THIRD_PARTY_RSS = 0x18;

    public static final int TYPE_SEARCH_ENGINE = 0xF0;

    public static final int TYPE_SEARCH_ENGINE_HOMEPAGE = 0xF1;

    public static final int TYPE_SEARCH_ENGINE_RESULTS_PAGES = 0xF2;

    public static final int STATE_UNPUBLISHED = Integer.MIN_VALUE; // Server Side State

    public static final int STATE_PUBLISHED = 0; // Server Side State

    public static final int STATE_DISABLE = Integer.MIN_VALUE; // Client Side State, STATE_DISABLE == STATE_UNPUBLISHED

    public static final int STATE_SUBSCRIBED = 0; // Client Side State, STATE_SUBSCRIBED == STATE_PUBLISHED

    public static final int STATE_UNSUBSCRIBED_URL1 = 0xF001; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL2 = 0xF002; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL3 = 0xF004; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL4 = 0xF008; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL5 = 0xF010; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL6 = 0xF020; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL7 = 0xF040; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL8 = 0xF080; // Client Side State

    public static final int STATE_UNSUBSCRIBED_URL9 = 0xF100; // Client Side State

    public static final int STATE_UNSUBSCRIBED = 0xF1FF; // Client Side State

    /** 
     * The id is a generated id under Servlet application, but it is NOT a generated id under Android application 
     * -- by York/GuangYu DENG
     */
    @DatabaseField(generatedId = true)
    private Long id;

    public final Long getId() {
        return id;
    }

    /** 
     * The id is a generated id under Servlet application, setID method can set a value in a POJO instance, but 
     * can NOT set a value into database. -- by York/GuangYu DENG
     */
    public final void setId(Long id) {
        this.id = id;
    }

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    @DatabaseField(columnName = "abbr", canBeNull = false)
    private String abbr;

    public final String getAbbr() {
        return abbr;
    }

    public final void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @DatabaseField(columnName = "logo", width = 255)
    private String logo;

    public final String getLogo() {
        return logo;
    }

    public final void setLogo(String logo) {
        this.logo = logo;
    }

    @DatabaseField(columnName = "domain", canBeNull = false)
    private String domain;

    public final String getDomain() {
        return domain;
    }

    public final void setDomain(String domain) {
        this.domain = domain;
    }

    @DatabaseField(columnName = "www", canBeNull = false, uniqueIndex = true, uniqueIndexName = "x_source_www")
    private String www;

    public final String getWWW() {
        return www;
    }

    public final void setWWW(String www) {
        this.www = www;
    }

    @DatabaseField(columnName = "description", width = 511)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@DatabaseField(columnName = "type", canBeNull = false)
	private Integer type;
	
	public final Integer getType() {
		return type;
	}
	
	public final void setType(Integer type) {
		this.type = type;
	}

    @DatabaseField(columnName = "country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @DatabaseField(columnName = "language")
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    @DatabaseField(columnName = "specific", width = 511)
    private String specific;

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    @DatabaseField(columnName = "priority", canBeNull = false)
    private Integer priority;

    public final Integer getPriority() {
        return priority;
    }

    public final void setPriority(Integer priority) {
        this.priority = priority;
    }

    @DatabaseField(columnName = "latency", canBeNull = false, defaultValue = "300")
    private Integer latency;

    public final Integer getLatency() {
        return latency;
    }

    public final void setLatency(Integer latency) {
        this.latency = latency;
    }

    @DatabaseField(columnName = "label1")
    private String label1;

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    @DatabaseField(columnName = "url1", canBeNull = false, width = 255)
    private String url1;

    public String getURL1() {
        return url1;
    }

    public void setURL1(String url1) {
        this.url1 = url1;
    }

    @DatabaseField(columnName = "limit1", defaultValue = "0")
    private Integer limit1;

    public final Integer getLimit1() {
        return limit1;
    }

    public final void setLimit1(final Integer limit1) {
        this.limit1 = limit1;
    }

    @DatabaseField(columnName = "type1", canBeNull = false)
    private Integer type1;

    public final Integer getType1() {
        return type1;
    }

    public final void setType1(Integer type1) {
        this.type1 = type1;
    }

    @DatabaseField(columnName = "encoding1", canBeNull = false)
    private String encoding1;

    public String getEncoding1() {
        return encoding1;
    }

    public void setEncoding1(String encoding1) {
        this.encoding1 = encoding1;
    }

    @DatabaseField(columnName = "label2")
    private String label2;

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    @DatabaseField(columnName = "url2", width = 255)
    private String url2;

    public String getURL2() {
        return url2;
    }

    public void setURL2(String url2) {
        this.url2 = url2;
    }

    @DatabaseField(columnName = "limit2", defaultValue = "0")
    private Integer limit2;
    
    public final Integer getLimit2() {
        return limit2;
    }

    public final void setLimit2(final Integer limit2) {
        this.limit2 = limit2;
    }

    @DatabaseField(columnName = "type2")
    private Integer type2;

    public final Integer getType2() {
        return type2;
    }

    public final void setType2(Integer type2) {
        this.type2 = type2;
    }

    @DatabaseField(columnName = "encoding2")
    private String encoding2;

    public String getEncoding2() {
        return encoding2;
    }

    public void setEncoding2(String encoding2) {
        this.encoding2 = encoding2;
    }

    @DatabaseField(columnName = "label3")
    private String label3;

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @DatabaseField(columnName = "url3", width = 255)
    private String url3;

    public String getURL3() {
        return url3;
    }

    public void setURL3(String url3) {
        this.url3 = url3;
    }

    @DatabaseField(columnName = "limit3", defaultValue = "0")
    private Integer limit3;

    public final Integer getLimit3() {
        return limit3;
    }

    public final void setLimit3(final Integer limit3) {
        this.limit3 = limit3;
    }

    @DatabaseField(columnName = "type3" )
    private Integer type3;

    public final Integer getType3() {
        return type3;
    }

    public final void setType3(Integer type3) {
        this.type3 = type3;
    }

    @DatabaseField(columnName = "encoding3")
    private String encoding3;

    public String getEncoding3() {
        return encoding3;
    }

    public void setEncoding3(String encoding3) {
        this.encoding3 = encoding3;
    }

    @DatabaseField(columnName = "label4")
    private String label4;

    public String getLabel4() {
        return label4;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    @DatabaseField(columnName = "url4", width = 255)
    private String url4;

    public String getURL4() {
        return url4;
    }

    public void setURL4(String url4) {
        this.url4 = url4;
    }

    @DatabaseField(columnName = "limit4", defaultValue = "0")
    private Integer limit4;

    public final Integer getLimit4() {
        return limit4;
    }

    public final void setLimit4(final Integer limit4) {
        this.limit4 = limit4;
    }

    @DatabaseField(columnName = "type4" )
    private Integer type4;

    public final Integer getType4() {
        return type4;
    }

    public final void setType4(Integer type4) {
        this.type4 = type4;
    }

    @DatabaseField(columnName = "encoding4")
    private String encoding4;

    public String getEncoding4() {
        return encoding4;
    }

    public void setEncoding4(String encoding4) {
        this.encoding4 = encoding4;
    }

    @DatabaseField(columnName = "label5")
    private String label5;

    public String getLabel5() {
        return label5;
    }

    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    @DatabaseField(columnName = "url5", width = 255)
    private String url5;

    public String getURL5() {
        return url5;
    }

    public void setURL5(String url5) {
        this.url5 = url5;
    }

    @DatabaseField(columnName = "limit5", defaultValue = "0")
    private Integer limit5;

    public final Integer getLimit5() {
        return limit5;
    }

    public final void setLimit5(final Integer limit5) {
        this.limit5 = limit5;
    }

    @DatabaseField(columnName = "type5" )
    private Integer type5;

    public final Integer getType5() {
        return type5;
    }

    public final void setType5(Integer type5) {
        this.type5 = type5;
    }

    @DatabaseField(columnName = "encoding5")
    private String encoding5;

    public String getEncoding5() {
        return encoding5;
    }

    public void setEncoding5(String encoding5) {
        this.encoding5 = encoding5;
    }

    @DatabaseField(columnName = "label6")
    private String label6;

    public String getLabel6() {
        return label6;
    }

    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    @DatabaseField(columnName = "url6", width = 255)
    private String url6;

    public String getURL6() {
        return url6;
    }

    public void setURL6(String url6) {
        this.url6 = url6;
    }

    @DatabaseField(columnName = "limit6", defaultValue = "0")
    private Integer limit6;

    public final Integer getLimit6() {
        return limit6;
    }

    public final void setLimit6(final Integer limit6) {
        this.limit6 = limit6;
    }

    @DatabaseField(columnName = "type6" )
    private Integer type6;

    public final Integer getType6() {
        return type6;
    }

    public final void setType6(Integer type6) {
        this.type6 = type6;
    }

    @DatabaseField(columnName = "encoding6")
    private String encoding6;

    public String getEncoding6() {
        return encoding6;
    }

    public void setEncoding6(String encoding6) {
        this.encoding6 = encoding6;
    }

    @DatabaseField(columnName = "label7")
    private String label7;

    public String getLabel7() {
        return label7;
    }

    public void setLabel7(String label7) {
        this.label7 = label7;
    }

    @DatabaseField(columnName = "url7", width = 255)
    private String url7;

    public String getURL7() {
        return url7;
    }

    public void setURL7(String url7) {
        this.url7 = url7;
    }

    @DatabaseField(columnName = "limit7", defaultValue = "0")
    private Integer limit7;

    public final Integer getLimit7() {
        return limit7;
    }

    public final void setLimit7(final Integer limit7) {
        this.limit7 = limit7;
    }

    @DatabaseField(columnName = "type7" )
    private Integer type7;

    public final Integer getType7() {
        return type7;
    }

    public final void setType7(Integer type7) {
        this.type7 = type7;
    }

    @DatabaseField(columnName = "encoding7")
    private String encoding7;

    public String getEncoding7() {
        return encoding7;
    }

    public void setEncoding7(String encoding7) {
        this.encoding7 = encoding7;
    }

    @DatabaseField(columnName = "label8")
    private String label8;

    public String getLabel8() {
        return label8;
    }

    public void setLabel8(String label8) {
        this.label8 = label8;
    }

    @DatabaseField(columnName = "url8", width = 255)
    private String url8;

    public String getURL8() {
        return url8;
    }

    public void setURL8(String url8) {
        this.url8 = url8;
    }

    @DatabaseField(columnName = "limit8", defaultValue = "0")
    private Integer limit8;

    public final Integer getLimit8() {
        return limit8;
    }

    public final void setLimit8(final Integer limit8) {
        this.limit8 = limit8;
    }

    @DatabaseField(columnName = "type8" )
    private Integer type8;

    public final Integer getType8() {
        return type8;
    }

    public final void setType8(Integer type8) {
        this.type8 = type8;
    }

    @DatabaseField(columnName = "encoding8")
    private String encoding8;

    public String getEncoding8() {
        return encoding8;
    }

    public void setEncoding8(String encoding8) {
        this.encoding8 = encoding8;
    }

    @DatabaseField(columnName = "label9")
    private String label9;

    public String getLabel9() {
        return label9;
    }

    public void setLabel9(String label9) {
        this.label9 = label9;
    }

    @DatabaseField(columnName = "url9", width = 255)
    private String url9;

    public String getURL9() {
        return url9;
    }

    public void setURL9(String url9) {
        this.url9 = url9;
    }

    @DatabaseField(columnName = "limit9", defaultValue = "0")
    private Integer limit9;

    public final Integer getLimit9() {
        return limit9;
    }

    public final void setLimit9(final Integer limit9) {
        this.limit9 = limit9;
    }

    @DatabaseField(columnName = "type9" )
    private Integer type9;

    public final Integer getType9() {
        return type9;
    }

    public final void setType9(Integer type9) {
        this.type9 = type9;
    }

    @DatabaseField(columnName = "encoding9")
    private String encoding9;

    public String getEncoding9() {
        return encoding9;
    }

    public void setEncoding9(String encoding9) {
        this.encoding9 = encoding9;
    }

    @DatabaseField(columnName = "version", canBeNull = false)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @DatabaseField(columnName = "insert_time", canBeNull = false, dataType = DataType.DATE_LONG)
    private Date insertTime;

    public final Date getInsertTime() {
        return insertTime;
    }

    public final void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @DatabaseField(columnName = "update_time", canBeNull = false, dataType = DataType.DATE_LONG)
    private Date updateTime;

    public final Date getUpdateTime() {
        return updateTime;
    }

    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @DatabaseField(columnName = "state", canBeNull = false, index = true, indexName = "x_source_state")
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer state) {
        this.state = state;
    }

    // Build select options for type
    
    // EN
    private static final Map<Integer, String> enTypes = new LinkedHashMap<Integer, String>();
    
    // zh-CN
    private static final Map<Integer, String> cnTypes = new LinkedHashMap<Integer, String>();

    /**
     * 
     * @return
     */
    public static Map<Integer, String> getTypes() {
    	return getTypes(Locale.ENGLISH);
    }
    
    /**
     * 
     * @param locale
     * @return
     */
    public static Map<Integer, String> getTypes(Locale locale) {
    	if (Locale.CHINESE.equals(locale)) {
	    	if (cnTypes.isEmpty()) {
	    		cnTypes.put(TYPE_OFFICIAL_SITE, "0x00 Official Website");
	    		cnTypes.put(TYPE_THIRD_PARTY_PLATFORM, "0x10 Third-Party Platform");
	    		cnTypes.put(TYPE_SEARCH_ENGINE, "0xf0 Search Engine");
	    	}
    		return cnTypes;
    	} else {
	    	if (enTypes.isEmpty()) {
	    		enTypes.put(TYPE_OFFICIAL_SITE, "0x00 Official Website");
	    		enTypes.put(TYPE_THIRD_PARTY_PLATFORM, "0x10 Third-Party Platform");
	    		enTypes.put(TYPE_SEARCH_ENGINE, "0xf0 Search Engine");
	    	}
	        return enTypes;
    	}
    }
    
    // Build select options for type1, type2, ..., type9 
    
    // EN
    private static final Map<Integer, String> enSubtypes = new LinkedHashMap<Integer, String>();
    
    // zh-CN
    private static final Map<Integer, String> cnSubtypes = new LinkedHashMap<Integer, String>();

    /**
     * 
     * @return
     */
    public static Map<Integer, String> getSubtypes() {
    	return getSubtypes(Locale.ENGLISH);
    }
    
    /**
     * 
     * @param locale
     * @return
     */
    public static Map<Integer, String> getSubtypes(Locale locale) {
    	if (Locale.CHINESE.equals(locale)) {
	    	if (cnSubtypes.isEmpty()) {
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_HOMEPAGE, "0x01 Official Website Homepage");
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_LIST_PAGES, "0x02 Official Website List Pages");
	    		cnSubtypes.put(TYPE_OFFICIAL_SITE_RSS, "0x08 Official Website RSS");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE, "0x11 Third-Party Platform Homepage");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_LIST_PAGES, "0x12 Third-Party Platform List Pages");
	    		cnSubtypes.put(TYPE_THIRD_PARTY_RSS, "0x18 Third-Party Platform RSS");
	    		cnSubtypes.put(TYPE_SEARCH_ENGINE_HOMEPAGE, "0xf1 Search Engine Homepage");
	    		cnSubtypes.put(TYPE_SEARCH_ENGINE_RESULTS_PAGES, "0xf2 Search Engine Results Pages");
	    	}
    		return cnSubtypes;
    	} else {
	    	if (enSubtypes.isEmpty()) {
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_HOMEPAGE, "0x01 Official Website Homepage");
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_LIST_PAGES, "0x02 Official Website List Pages");
	    		enSubtypes.put(TYPE_OFFICIAL_SITE_RSS, "0x08 Official Website RSS");
	    		enSubtypes.put(TYPE_THIRD_PARTY_PLATFORM_HOMEPAGE, "0x11 Third-Party Platform Homepage");
	    		enSubtypes.put(TYPE_THIRD_PARTY_LIST_PAGES, "0x12 Third-Party Platform List Pages");
	    		enSubtypes.put(TYPE_THIRD_PARTY_RSS, "0x18 Third-Party Platform RSS");
	    		enSubtypes.put(TYPE_SEARCH_ENGINE_HOMEPAGE, "0xf1 Search Engine Homepage");
	    		enSubtypes.put(TYPE_SEARCH_ENGINE_RESULTS_PAGES, "0xf2 Search Engine Results Pages");
	    	}
	        return enSubtypes;
    	}
    }
    
    @Override
    public String toString() {
        if (id != null) return (Long.toString(id));
        else return this.name;
    }
}
