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


public class NewsTopicFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_select_topic, container, false);

        Button btnScience = view.findViewById(R.id.btn_science);
        Button btnPolitics = view.findViewById(R.id.btn_politics);
        Button btnEconomy = view.findViewById(R.id.btn_economy);
        Button btnSocial = view.findViewById(R.id.btn_social);
        Button btnBack = view.findViewById(R.id.btn_news_back);

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });



        btnScience.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_topic_to_list);
        });

        btnPolitics.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_topic_to_list);
        });

        btnEconomy.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_topic_to_list);
        });

        btnSocial.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_topic_to_list);
        });

        return view;
    }
}