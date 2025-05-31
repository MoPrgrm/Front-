package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;

public class QuizLanguageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_language, container, false);

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        Button btnEnglish = view.findViewById(R.id.btnEnglish);
        Button btnJapanese = view.findViewById(R.id.btnJapanese);
        Button btnChinese = view.findViewById(R.id.btnChinese);

        btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        btnEnglish.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "English");
//            Navigation.findNavController(v).navigate(R.id.action_quiz_language_to_quiz, args);
        });

        btnJapanese.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "Japanese");
//            Navigation.findNavController(v).navigate(R.id.action_quiz_language_to_quiz, args);
        });

        btnChinese.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "Chinese");
//            Navigation.findNavController(v).navigate(R.id.action_quiz_language_to_quiz, args);
        });

        return view;
    }
}