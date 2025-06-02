package kr.co.gachon.moproject_d.ui.wordlist;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import kr.co.gachon.moproject_d.data.WordEntity;
import kr.co.gachon.moproject_d.data.WordRepository;
import java.util.List;

public class WordlistViewModel extends AndroidViewModel {
    private final WordRepository repository;
    private final MutableLiveData<String> selectedLanguage = new MutableLiveData<>();
    private final LiveData<List<WordEntity>> wordsByLanguage;

    public WordlistViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);

        // 선택된 언어에 따라 단어 목록이 변경되도록 설정
        wordsByLanguage = Transformations.switchMap(selectedLanguage, language -> {
            if (language != null && !language.isEmpty()) {
                return repository.getWordsByLanguage(language);
            }
            return repository.getAllWords();
        });
    }

    public LiveData<List<WordEntity>> getWordsByLanguage() {
        return wordsByLanguage;
    }

    public void setSelectedLanguage(String language) {
        selectedLanguage.setValue(language);
    }

    public void insert(WordEntity word) {
        repository.insert(word);
    }

    public void delete(WordEntity word) {
        repository.delete(word);
    }
}