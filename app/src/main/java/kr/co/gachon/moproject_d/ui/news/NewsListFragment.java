package kr.co.gachon.moproject_d.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import kr.co.gachon.moproject_d.R;


public class NewsListFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        Button btnNewsList1 = view.findViewById(R.id.btn_news_list_1);
        Button btnBack = view.findViewById(R.id.btn_news_back);

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });


        btnNewsList1.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });


        return view;
    }
}
