package com.crosscode.rule.engine.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CrossCodeTableRequest implements Serializable {

	private String columnName;
	private String columnValue;
	private boolean isUpdated;
	private boolean isValid;

	public CrossCodeTableRequest() {
	}

	public CrossCodeTableRequest(final String columnName, final String columnValue, final boolean isUpdated) {
		super();
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.isUpdated = isUpdated;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(final String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(final String columnValue) {
		this.columnValue = columnValue;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(final boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(final boolean isValid) {
		this.isValid = isValid;
	}
	
	

}
