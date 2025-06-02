// WordEntity.java
package kr.co.gachon.moproject_d.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class WordEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String word;
    public String meaning;
    public String language;

    public WordEntity(String word, String meaning, String language) {
        this.word = word;
        this.meaning = meaning;
        this.language = language;
    }

    public String getWord() { return word; }
    public String getMeaning() { return meaning; }
    public String getLanguage() { return language; }
}
