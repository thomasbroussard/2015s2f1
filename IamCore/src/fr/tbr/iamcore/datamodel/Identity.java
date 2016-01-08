/**
 * 
 */
package fr.tbr.iamcore.datamodel;

import java.util.Date;

/**
 * @author tbrou
 *
 */
public class Identity {
	private String uid;
	private String email;
	private String displayName;
	
	private Date birthDate;
	
	
	public Identity() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param uid
	 * @param email
	 * @param displayName
	 */
	public Identity(String uid, String email, String displayName) {
		this.uid = uid;
		this.email = email;
		this.displayName = displayName;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", email=" + email + ", displayName="
				+ displayName + ", birthDate=" + birthDate + "]";
	}

}
