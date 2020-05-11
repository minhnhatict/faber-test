package faber.codetest.nhat.entity;

/*
  This class is for handle operation of authentication
   and authorization in System
   IMPORTANT: Not handling info of Customer
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fbt_user")
public class User extends BaseEntity {
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private String phone;

    @Column
    private String roleName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
