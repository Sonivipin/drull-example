package com.crosscode.rule.engine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "crossCodeSourceFiile")
public class CrossCodeTableResponse implements Serializable {

	@Id
	@Column(name = "id", nullable = false, length = 25)
	private int id;

	@Column(name = "filename", nullable = false, length = 15)
	private String filename ;

	@Column(name = "type", length = 15)
	private String type;

	/**
	 * Default constructor for entity bean creation.
	 */
	public CrossCodeTableResponse() {
	}

	/**
	 * 
	 * @param id
	 * @param filename
	 * @param type
	 */
	public CrossCodeTableResponse(final int id, final String filename, final String type) {
		super();
		this.id = id;
		this.filename = filename;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(final String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
