/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "myuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyUser.findAll", query = "SELECT m FROM MyUser m")
    , @NamedQuery(name = "MyUser.findByUid", query = "SELECT m FROM MyUser m WHERE m.uid = :uid")
    , @NamedQuery(name = "MyUser.findByFname", query = "SELECT m FROM MyUser m WHERE m.fname = :fname")
    , @NamedQuery(name = "MyUser.findByLname", query = "SELECT m FROM MyUser m WHERE m.lname = :lname")
    , @NamedQuery(name = "MyUser.findByUsername", query = "SELECT m FROM MyUser m WHERE m.username = :username")
    , @NamedQuery(name = "MyUser.findByPasswd", query = "SELECT m FROM MyUser m WHERE m.passwd = :passwd")
    , @NamedQuery(name = "MyUser.findByEnabled", query = "SELECT m FROM MyUser m WHERE m.enabled = :enabled")})
public class MyUser implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 68)
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany
    @JoinTable(name = "user_role", 
            joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "uid")}, 
            inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "rid")})
    private Set<Role> roleSet;

    public MyUser() {
    }

    public MyUser(Integer uid) {
        this.uid = uid;
    }

    public MyUser(Integer uid, String fname, String lname, String username, String passwd) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.passwd = passwd;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @XmlTransient
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "emergon.entity.MyUser[ uid=" + uid + " ]";
    }
    
}
