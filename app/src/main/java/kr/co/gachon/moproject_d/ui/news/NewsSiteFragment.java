package kr.co.gachon.moproject_d.ui.news;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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


public class NewsSiteFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_select_site, container, false);

        Button btnCNN = view.findViewById(R.id.btn_cnn);
        Button btnBBC = view.findViewById(R.id.btn_bbc);
        Button btnREUTERS = view.findViewById(R.id.btn_reuters);
        Button btnAP = view.findViewById(R.id.btn_ap);
        Button btnBack = view.findViewById(R.id.btn_news_back);

        SharedPreferences prefs = requireActivity().getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });

        btnCNN.setOnClickListener(v -> {
            editor.putString("site","cnn");
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_site_to_topic);
        });

        btnBBC.setOnClickListener(v -> {
            editor.putString("site","bbc-news");
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_site_to_topic);
        });

        btnREUTERS.setOnClickListener(v -> {
            editor.putString("site","reuters");
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_site_to_topic);
        });

        btnAP.setOnClickListener(v -> {
            editor.putString("site","associated-press");
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_site_to_topic);
        });

        return view;
    }
}