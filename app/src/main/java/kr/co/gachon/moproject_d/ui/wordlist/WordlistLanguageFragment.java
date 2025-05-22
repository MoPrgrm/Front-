package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;

public class WordlistLanguageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wordlist_language, container, false);
        
        TextView tvWordTitle = view.findViewById(R.id.tvWordTitle);
        
        // Get the selected language from arguments
        if (getArguments() != null) {
            String selectedLanguage = getArguments().getString("selected_language", "English");
            tvWordTitle.setText("단어-" + selectedLanguage);
        }
        
        tvWordTitle.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.wordSelectFragment);
        });
        
        return view;
    }
} 