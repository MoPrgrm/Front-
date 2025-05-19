package kr.co.gachon.moproject_d.ui.wordlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import kr.co.gachon.moproject_d.databinding.FragmentWordlistBinding;

public class WordlistFragment extends Fragment {

    private FragmentWordlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WordlistViewModel dashboardViewModel =
                new ViewModelProvider(this).get(WordlistViewModel.class);

        binding = FragmentWordlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textWordlist;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}