package com.plusplm.auth;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(
//    base = "ou=people",
    objectClasses = { "person", "inetOrgPerson", "top", "organizationalPerson" }
)
final public class OuPeopleModel {
    @Id
    private Name id;

    @Attribute(name = "uid")
    private String uid;
    
    @Attribute(name = "userPassword")
    private String userPassword;

	@Attribute(name = "sn")
    private String sn;

    @Attribute(name = "cn")
    private String cn;

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }
    
    public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

    public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", sn='" + sn + '\'' +
                ", password='" + userPassword + '\'' +
                ", cn='" + cn + '\'' +
                '}';
    }
}
