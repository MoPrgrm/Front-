package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;

public class WordSelectFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_select, container, false);

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        LinearLayout btnEnglish = view.findViewById(R.id.btnEnglish);
        LinearLayout btnChinese = view.findViewById(R.id.btnChinese);
        LinearLayout btnJapanese = view.findViewById(R.id.btnJapanese);

        btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        btnEnglish.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "English");
            Navigation.findNavController(v).navigate(R.id.action_word_select_to_wordlist_language, args);
        });
        btnChinese.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "中国語");
            Navigation.findNavController(v).navigate(R.id.action_word_select_to_wordlist_language, args);
        });
        btnJapanese.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("selected_language", "日本語");
            Navigation.findNavController(v).navigate(R.id.action_word_select_to_wordlist_language, args);
        });
        return view;
    }
}