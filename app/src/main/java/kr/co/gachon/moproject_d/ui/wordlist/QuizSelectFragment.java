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

public class QuizSelectFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_select, container, false);
        TextView tvTitleQuiz = view.findViewById(R.id.tvTitleQuiz);
        tvTitleQuiz.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.navigation_wordlist);
        });
        return view;
    }
} 