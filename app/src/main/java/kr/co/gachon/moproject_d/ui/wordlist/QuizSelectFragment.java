package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;

public class QuizSelectFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_select, container, false);

        TextView tvTitleQuiz = view.findViewById(R.id.tvTitleQuiz);
        Button btnEnglish = view.findViewById(R.id.btnEnglish);
        Button btnChinese = view.findViewById(R.id.btnChinese);
        Button btnJapanese = view.findViewById(R.id.btnJapanese);

        tvTitleQuiz.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        btnEnglish.setOnClickListener(v -> {
            // TODO: Implement English quiz
            Toast.makeText(requireContext(), "English quiz coming soon", Toast.LENGTH_SHORT).show();
        });

        btnChinese.setOnClickListener(v -> {
            // TODO: Implement Chinese quiz
            Toast.makeText(requireContext(), "Chinese quiz coming soon", Toast.LENGTH_SHORT).show();
        });

        btnJapanese.setOnClickListener(v -> {
            // TODO: Implement Japanese quiz
            Toast.makeText(requireContext(), "Japanese quiz coming soon", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}