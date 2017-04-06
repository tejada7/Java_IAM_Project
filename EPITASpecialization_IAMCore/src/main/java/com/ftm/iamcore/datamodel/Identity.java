/**
 * Identity.java
 */

package com.ftm.iamcore.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The {@code Identity} class defines the 
 * fields and methods based on the requirements
 * Contain Hibernate annotations to map the Class to a database
 * @author Favio
 *
 */
@Entity
@Table(name="IDENTITIES")
public class Identity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int uid;

	@Column(name="displayName")
	protected String displayName;
	
	@Column(name="lastName")
	protected String lastName;

	@Column(name="email")
	protected String email;

	@Column(name="birthDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date birthDate;

	//Extra attributes
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="name")
	@Column(name="value")
	@CollectionTable(name="attributes", joinColumns=@JoinColumn(name="uid"))		
	protected Map<String,String> attributes = new HashMap<>();
	
	//Constructors
	public Identity() {		
		//attributes = new HashMap<>();
	}
	
	public Identity(String displayName) {
		this.displayName = displayName;
	}

	public Identity(String displayName, String email, Date birthDate, Map<String, String> attributes) {
		this.displayName = displayName;
		this.email = email;
		this.birthDate = birthDate;
		this.attributes = attributes;
	}
	
	public Identity(String displayName, String lastName, String email, Date birthDate, Map<String, String> attributes) {
		this.displayName = displayName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.attributes = attributes;
	}
	
	public Identity(String displayName, String email, Date birthDate) {
		this.displayName = displayName;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	public Identity(String displayName, String lastName, String email, Date birthDate) {
		this.displayName = displayName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}

	//Getters and setters
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {				
		return "Identity [displayName=" + displayName + ", lastName=" + lastName + ", uid=" + uid + ", email=" + email + ", birthday= " + birthDate + " attributes= " + printAttributes() + "]";
	}

	/**
	 * Internal method that formats the output of the extra attributes
	 * @return a String containing the attributes' name and value if exist
	 */
	private String printAttributes() {
		StringBuilder string = new StringBuilder();
		string.append('[');								
		for(Map.Entry<String, String> value : attributes.entrySet()) {
			string.append(value.getKey());
			string.append('=');
			string.append(value.getValue());
			string.append(',');
		}
		string.append(']');		
		return string.toString();
	}

	/**
	 * Transform the Map attributes into an arrayList
	 * @return a new arrayList
	 */
	public List<Map.Entry<String, String>> getAttributesList() {
		Set<Map.Entry<String, String>> attributeSet = attributes.entrySet();
		return new ArrayList<Map.Entry<String, String>>(attributeSet);
	}
	
	/**
	 * Receive a {@code List<Map.Entry<String, String>> of attributes} to set the {@code Map<String, String of attributes>}}
	 * @param listAttributes set of attributes
	 */
	public void setAttributesList(List<Map.Entry<String, String>> listAttributes) {
		for (final Map.Entry<String, String> entry : listAttributes) {
			attributes.put(entry.getKey(), entry.getValue());
		}
	}
}
