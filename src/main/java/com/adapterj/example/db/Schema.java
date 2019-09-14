package com.adapterj.example.db;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "t_schema")
public class Schema implements Serializable {

	private static final long serialVersionUID = -7013716957461942483L;
	
	public static final String SUBSTRING = "SUBSTR";
	public static final String LENGTH = "LENGTH";
    
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VERSION = "version";
    public static final String COLUMN_STATE = "state";

    @DatabaseField(generatedId = true)
    private Long id;

    public final Long getId() {
        return id;
    }

    @DatabaseField(columnName = "version", canBeNull = false, uniqueIndex = true, uniqueIndexName = "x_sqlite_ver")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
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

    @DatabaseField(columnName = "state", canBeNull = false)
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id=").append(id);
        s.append(", ").append("version=").append(version);
        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        s.append(", ").append("insert_time=").append((insertTime == null) ? null : formatter.format(insertTime));
        s.append(", ").append("update_time=").append((updateTime == null) ? null : formatter.format(updateTime));
        s.append(", ").append("state=").append(state);
        return s.toString();
    }
}
