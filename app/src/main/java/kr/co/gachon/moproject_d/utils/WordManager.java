package kr.co.gachon.moproject_d.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kr.co.gachon.moproject_d.model.Word;

public class WordManager {
    private static final String PREF_NAME = "word_preferences";
    private static final String KEY_WORDS = "words";
    private final SharedPreferences preferences;
    private final Gson gson;

    public WordManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveWord(Word word) {
        List<Word> words = getWords();
        words.add(word);
        saveWords(words);
    }

    public void saveWords(List<Word> words) {
        String json = gson.toJson(words);
        preferences.edit().putString(KEY_WORDS, json).apply();
    }

    public List<Word> getWords() {
        String json = preferences.getString(KEY_WORDS, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Word>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public List<Word> getWordsByLanguage(String language) {
        List<Word> allWords = getWords();
        List<Word> filteredWords = new ArrayList<>();
        for (Word word : allWords) {
            if (word.getLanguage().equals(language)) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }

    public void deleteWord(Word wordToDelete) {
        List<Word> words = getWords();
        words.removeIf(word ->
                word.getWord().equals(wordToDelete.getWord()) &&
                        word.getLanguage().equals(wordToDelete.getLanguage())
        );
        saveWords(words);
    }
}