/*
 * Copyright (c) 2015 www.wellpoint.com.  All rights reserved.
 *
 * This program contains proprietary and confidential information and trade
 * secrets of Wellpoint. This program may not be duplicated, disclosed or
 * provided to any third parties without the prior written consent of
 * Wellpoint. Disassembling or decompiling of the software and/or reverse
 * engineering of the object code are prohibited.
 * 
 * 
 */
package com.wellpoint.mobility.aggregation.persistence.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the APPLE_APP_INFO database table.
 * 
 * @author frank.garber@wellpoint.com
 * 
 */
@Entity
@NamedQueries(
	{ 
		@NamedQuery(name = "MapOfBlob.get.by.key.latestVersion", 
			query = "SELECT po FROM MapOfBlob po where po.theKey = :key and po.version = (select max(version) from MapOfBlob pi where pi.theKey = :key)"),
		@NamedQuery(name = "MapOfBlob.get.by.key.and.version", 
			query = "SELECT po FROM MapOfBlob po where po.theKey = :key and po.version = :version"),
		@NamedQuery(name = "MapOfBlob.get.by.like.key",
			query = "SELECT po FROM MapOfBlob po where po.theKey LIKE :key"),
		@NamedQuery(name = "MapOfBlob.delete.by.idList", 
			query = "delete FROM MapOfBlob po where po.id in (:IdList)")
	}
)
@Table(name = "BLOB_MAP")
public class MapOfBlob extends AbstractAuditedEntity
{

	/**
	 * Default serial version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key. Generated by the underlying DB.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** (non-Javadoc)
	 * @see com.wellpoint.mobilemetrics.persistence.domain.AbstractEntity#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * The binary value being stored / retrieved
	 */
	@Lob
	@Column(name = "VALUE")
	protected byte[] value;

	/**
	 * The 'key' used to retrieve the value. 
	 */
	@Column(name = "THE_KEY")
	protected String theKey;



	/**
	 * Getter method
	 * 
	 * @return the current value
	 */
	public byte[] getValue()
	{
		return value;
	}

	/**
	 * Set the underlying 'value'.
	 * 
	 * @param value the new value for this
	 */
	public void setValue(final byte[] value)
	{
		this.value = value;
	}

	/**
	 * Getter method
	 * 
	 * @return the current value
	 */
	public String getTheKey()
	{
		return theKey;
	}

	
	/**
	 * Set the current value
	 * 
	 * @param theKey the new value
	 */
	public void setTheKey(final String theKey)
	{
		this.theKey = theKey;
	}

	/** (non-Javadoc)
	 * @see 
	 * com.wellpoint.mobilemetrics.persistence.domain.AbstractAuditedEntity#toString()
	 */
	@Override
	public String toString()
	{
		return "MapOfBlob [id=" + id + ", value len=" + value.length + ", theKey=" + theKey + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode()
	{
		int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((theKey == null) ? 0 : theKey.hashCode());
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapOfBlob other = (MapOfBlob) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (theKey == null)
		{
			if (other.theKey != null)
				return false;
		}
		else if (!theKey.equals(other.theKey))
			return false;
		if (!Arrays.equals(value, other.value))
			return false;
		return true;
	}

	public void setId(Long id) {
		this.id = id;
	}

} // of class
