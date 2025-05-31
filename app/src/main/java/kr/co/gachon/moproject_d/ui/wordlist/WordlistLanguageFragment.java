package kr.co.gachon.moproject_d.ui.wordlist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.model.Word;
import kr.co.gachon.moproject_d.utils.WordManager;
import java.util.List;

public class WordlistLanguageFragment extends Fragment {
    private WordManager wordManager;
    private LinearLayout wordListContainer;
    private String selectedLanguage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordManager = new WordManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wordlist_language, container, false);

        wordListContainer = view.findViewById(R.id.wordListContainer);
        TextView tvWordTitle = view.findViewById(R.id.tvWordTitle);
        ImageButton btnAddWord = view.findViewById(R.id.btnAddWord);
        ImageButton btnBack = view.findViewById(R.id.btnBack);

        // Get the selected language from arguments
        if (getArguments() != null) {
            selectedLanguage = getArguments().getString("selected_language", "English");
            tvWordTitle.setText("단어-" + selectedLanguage);
        }

        btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        btnAddWord.setOnClickListener(v -> showAddWordDialog());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadWords();
    }

    private void showAddWordDialog() {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_word, null);
        EditText etWord = dialogView.findViewById(R.id.etWord);
        EditText etMeaning = dialogView.findViewById(R.id.etMeaning);

        new AlertDialog.Builder(requireContext())
                .setTitle("단어 추가")
                .setView(dialogView)
                .setPositiveButton("추가", (dialog, which) -> {
                    String word = etWord.getText().toString().trim();
                    String meaning = etMeaning.getText().toString().trim();

                    if (word.isEmpty() || meaning.isEmpty()) {
                        Toast.makeText(requireContext(), "단어와 의미를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Word newWord = new Word(word, meaning, selectedLanguage);
                    wordManager.saveWord(newWord);
                    loadWords();
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private void loadWords() {
        if (wordListContainer == null) return;

        wordListContainer.removeAllViews();
        List<Word> words = wordManager.getWordsByLanguage(selectedLanguage);

        for (Word word : words) {
            View wordItem = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_word, wordListContainer, false);

            TextView tvWord = wordItem.findViewById(R.id.tvWord);
            TextView tvMeaning = wordItem.findViewById(R.id.tvMeaning);

            tvWord.setText(word.getWord());
            tvMeaning.setText(word.getMeaning());

            // 단어 항목을 길게 누르면 삭제 다이얼로그 표시
            wordItem.setOnLongClickListener(v -> {
                showDeleteWordDialog(word);
                return true;
            });

            wordListContainer.addView(wordItem);
        }
    }

    private void showDeleteWordDialog(Word word) {
        new AlertDialog.Builder(requireContext())
                .setTitle("단어 삭제")
                .setMessage("이 단어를 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    wordManager.deleteWord(word);
                    loadWords();
                })
                .setNegativeButton("취소", null)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWords();
    }
}