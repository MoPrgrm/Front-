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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.data.WordEntity;

public class WordlistLanguageFragment extends Fragment {
    private WordlistViewModel viewModel;
    private LinearLayout wordListContainer;
    private String selectedLanguage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WordlistViewModel.class);
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

            // ViewModel에 선택된 언어 설정
            viewModel.setSelectedLanguage(selectedLanguage);
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

        // LiveData 관찰하여 단어 목록 업데이트
        viewModel.getWordsByLanguage().observe(getViewLifecycleOwner(), words -> {
            loadWords(words);
        });
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

                    WordEntity newWord = new WordEntity(word, meaning, selectedLanguage);
                    viewModel.insert(newWord);
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private void loadWords(java.util.List<WordEntity> words) {
        if (wordListContainer == null) return;

        wordListContainer.removeAllViews();

        for (WordEntity word : words) {
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

    private void showDeleteWordDialog(WordEntity word) {
        new AlertDialog.Builder(requireContext())
                .setTitle("단어 삭제")
                .setMessage("이 단어를 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    viewModel.delete(word);
                })
                .setNegativeButton("취소", null)
                .show();
    }
}