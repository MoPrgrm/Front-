package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.databinding.FragmentWordlistBinding;

public class WordlistFragment extends Fragment {

    private FragmentWordlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWordlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnWord.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_wordlist_to_word_select)
        );
        binding.btnQuiz.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_wordlist_to_quiz_select)
        );
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}