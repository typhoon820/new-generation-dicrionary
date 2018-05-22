package com.assessment.project.model;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
@Entity
@Table(name = "role", schema = "project")
public class RoleEntity {
    private int id;
    private String role;
    private List<UserEntity> users;

/*    public RoleEntity(){
        this.id = 2;
        this.role = "user";
    }*/

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
