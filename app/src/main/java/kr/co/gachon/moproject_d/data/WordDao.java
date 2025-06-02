package kr.co.gachon.moproject_d.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM words")
    LiveData<List<WordEntity>> getAllWords();

    // 언어별로 단어를 조회하는 메소드 추가
    @Query("SELECT * FROM words WHERE language = :language")
    LiveData<List<WordEntity>> getWordsByLanguage(String language);

    // 동기적으로 언어별 단어를 조회하는 메소드 (즉시 결과 필요할 때)
    @Query("SELECT * FROM words WHERE language = :language")
    List<WordEntity> getWordsByLanguageSync(String language);

    @Insert
    void insert(WordEntity word);

    @Delete
    void delete(WordEntity word);
}