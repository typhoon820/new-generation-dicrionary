package com.assessment.project.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 27.05.2017.
 */
@Entity
@Table(name = "level", schema = "project")
public class LevelEntity {
    private int id;
    private String level;
    private List<UserEntity> users;

    @OneToMany(mappedBy = "level",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }



    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LevelEntity that = (LevelEntity) o;

        if (id != that.id) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
