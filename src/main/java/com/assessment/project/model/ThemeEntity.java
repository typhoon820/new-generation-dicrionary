package com.assessment.project.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
@Entity
@Table(name = "theme", schema = "project")
public class ThemeEntity {
    private int id;
    private String theme;
    private List<TranslationEntity> wordsOfThatTheme;

    @OneToMany(mappedBy = "theme")
    public List<TranslationEntity> getWordsOfThatTheme() {
        return wordsOfThatTheme;
    }

    public void setWordsOfThatTheme(List<TranslationEntity> wordsOfThatTheme) {
        this.wordsOfThatTheme = wordsOfThatTheme;
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
    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThemeEntity that = (ThemeEntity) o;

        if (id != that.id) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }
}
