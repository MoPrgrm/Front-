package kr.co.gachon.moproject_d.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordRepository {
    private final WordDao wordDao;
    private final LiveData<List<WordEntity>> allWords;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public WordRepository(Application application) {
        WordDatabase db = WordDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return allWords;
    }

    // 언어별 단어 조회 메소드 추가
    public LiveData<List<WordEntity>> getWordsByLanguage(String language) {
        return wordDao.getWordsByLanguage(language);
    }

    // 동기적으로 언어별 단어 조회 (백그라운드 스레드에서 실행)
    public List<WordEntity> getWordsByLanguageSync(String language) {
        return wordDao.getWordsByLanguageSync(language);
    }

    public void insert(WordEntity word) {
        executor.execute(() -> wordDao.insert(word));
    }

    public void delete(WordEntity word) {
        executor.execute(() -> wordDao.delete(word));
    }
}