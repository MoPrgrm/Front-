package kr.co.gachon.moproject_d.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import kr.co.gachon.moproject_d.ChangeAccountActivity;
import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNewsGoSite.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_to_news_site);
        });

        binding.btnDailyNews1.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_to_news_view);
        });

        binding.btnDailyNews2.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_to_news_view);
        });

        binding.btnDailyNews3.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_to_news_view);
        });

//        newsViewModel.getText().observe(getViewLifecycleOwner(), text -> {
//            binding.textNews.setText(text);
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}