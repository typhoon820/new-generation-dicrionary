package com.assessment.project.model;

import javax.persistence.*;

/**
 * Created by Никита on 25.05.2017.
 */
@Entity
@Table(name = "translation",
        schema = "project"
)
public class TranslationEntity {
    private int id;
    private String translation;
    private WordEntity nativeWord;
    private ThemeEntity theme;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "theme_ID")
    public ThemeEntity getTheme() {
        return theme;
    }

    public void setTheme(ThemeEntity theme) {
        this.theme = theme;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_ID")
    public WordEntity getNativeWord() {
        return nativeWord;
    }

    public void setNativeWord(WordEntity nativeWord) {
        this.nativeWord = nativeWord;
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
    @Column(name = "translation")
    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslationEntity that = (TranslationEntity) o;

        if (id != that.id) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        return result;
    }
}
