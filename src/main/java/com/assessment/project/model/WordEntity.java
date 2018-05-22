package com.assessment.project.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
@Entity
@Table(name = "word", schema = "project")
public class WordEntity {
    private int id;
    private String word;
    private UserEntity user;
    private List<TranslationEntity> meanings = new ArrayList<>();

    public void setMeanings(List<TranslationEntity> meanings) {
        this.meanings = meanings;
    }

    @OneToMany(mappedBy = "nativeWord", fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<TranslationEntity> getMeanings() {
        return meanings;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
    @Column(name = "word")
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordEntity that = (WordEntity) o;

        if (id != that.id) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        return result;
    }
}
