package kr.co.gachon.moproject_d.model;

public class Word {
    private String word;
    private String meaning;
    private String language;

    public Word(String word, String meaning, String language) {
        this.word = word;
        this.meaning = meaning;
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}