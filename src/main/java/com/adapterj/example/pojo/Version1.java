package com.adapterj.example.pojo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * SQL: 
 * 
 * create table t_version (
 * id                   INTEGER                        not null,
 * id_source            INTEGER,
 * type                 INTEGER                        not null,
 * version              VARCHAR(31)                    not null,
 * year                 INTEGER                        not null,
 * serial               INTEGER                        not null,
 * insert_time          TIMESTAMP                      not null,
 * update_time          TIMESTAMP                      not null,
 * state                INTEGER                        not null,
 * primary key (id),
 * foreign key (id_source)
 *         references t_source (id)
 * );
 *
 * create unique index x_version_unique on t_version (
 *        id_source ASC, type ASC, year ASC, serial ASC
 * );
 *
 * create index x_version_version on t_version (
 *        version ASC
 * );
 *
 * create index x_version_state on t_version (
 *        state ASC
 * );
 * 
 *
 * @author York/GuangYu DENG <york.deng@qq.com>
 *
 */
@DatabaseTable(tableName = "t_version")
public class Version1 implements Serializable {

    private static final long serialVersionUID = -6126939183193354845L;

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ID_SOURCE = "id_source";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_VERSION = "version";

    public static final String COLUMN_YEAR = "year";

    public static final String COLUMN_SERIAL = "serial";

    public static final String COLUMN_INSERT_TIME = "insert_time";

    public static final String COLUMN_UPDATE_TIME = "update_time";

    public static final String COLUMN_STATE = "state";

    public static final int TYPE_SOURCE = 0x01;

    public static final int TYPE_METADATE_OF_ARTICLE = 0x10;

    public static final int TYPE_METADATE_OF_ARTICLE_LIST = 0xa0;

    public static final int TYPE_METADATE_OF_SEARCH_RESULT_LIST = 0xf0;

    public static final int STATE_DISABLE = Integer.MIN_VALUE;

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
    public final void setID(Long id) {
        this.id = id;
    }

    @DatabaseField(columnName = "id_source", foreign = true, uniqueIndex = true, uniqueIndexName = "x_version_unique")
    private Source1 source;
    
    public final Source1 getSource() {
        return source;
    }

    public final void setSource(Source1 source) {
        this.source = source;
    }

    @DatabaseField(columnName = "type", canBeNull = false, uniqueIndex = true, uniqueIndexName = "x_version_unique")
    private Integer type;

    public final Integer getType() {
        return type;
    }

    public final void setType(Integer type) {
        this.type = type;
    }

    @DatabaseField(columnName = "version", canBeNull = false, index = true, indexName = "x_version_version")
    private String version;

    public String getVersion() {
    	return version != null ? version : (year != null && serial != null ? version(year, serial) : null);
    } // York/GuangYu DENG

    public void setVersion(String version) {
        this.version = version;
    }

    @DatabaseField(columnName = "year", canBeNull = false, uniqueIndex = true, uniqueIndexName = "x_version_unique")
    private Integer year;

    public final Integer getYear() {
        return year != null ? year : (version != null ? year(version) : null);
    }

    public final void setYear(Integer year) {
        this.year = year;
    }

    @DatabaseField(columnName = "serial", canBeNull = false, uniqueIndex = true, uniqueIndexName = "x_version_unique")
    private Integer serial;

    public final Integer getSerial() {
    	return serial != null ? serial : (version != null ? serial(version) : null);
    }

    public final void setSerial(Integer serial) {
        this.serial = serial;
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

    @DatabaseField(columnName = "state", canBeNull = false, index = true, indexName = "x_version_state")
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if (id != null) return (Long.toString(id));
        else return this.version;
    }
    
    // protected methods
    
    protected static final NumberFormat format = new DecimalFormat("000"); // York/GuangYu DENG
    
    protected final String version(final Integer year, final Integer serial) {
    	return (new StringBuffer()).append('v').append(year).append('_').append(format.format(serial)).toString();
    } // York/GuangYu DENG
    
    protected final Integer year(final String version) {
    	if (version == null) return (null);
    	if (!version.startsWith("v")) return (null);
    	if (version.length() < 9) return (null); // Such as: v2019_001
    	try { return Integer.parseInt(version.substring(1, 5)); } catch (Throwable ignore) { };
    	return (null);
    } // York/GuangYu DENG
    
    protected final Integer serial(final String version) {
    	if (version == null) return (null);
    	if (!version.startsWith("v")) return (null);
    	if (version.length() < 9) return (null); // Such as: v2019_001
    	try { return Integer.parseInt(version.substring(6, 9)); } catch (Throwable ignore) { };
    	return (null);
    } // York/GuangYu DENG
}
