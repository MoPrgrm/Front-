package kr.co.gachon.moproject_d.ui.wordlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WordlistViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WordlistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}