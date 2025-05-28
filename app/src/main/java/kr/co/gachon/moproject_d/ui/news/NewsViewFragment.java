package kr.co.gachon.moproject_d.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import kr.co.gachon.moproject_d.R;



public class NewsViewFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_view, container, false);

        Button btnBack = view.findViewById(R.id.btn_news_back);
        ImageButton btnWord = view.findViewById(R.id.btn_news_view_word);

        TextView txtNewsTitle = view.findViewById(R.id.txt_news_view_title);
        TextView txtNewsContent = view.findViewById(R.id.txt_news_view_content);
        TextView txtNewsWord = view.findViewById(R.id.txt_news_view_word);
        TextView txtNewsWordDescription = view.findViewById(R.id.txt_news_view_description);

        ImageView imgNewsImg = view.findViewById(R.id.img_news_view_img);


        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });

        btnWord.setOnClickListener(v -> {
            Toast.makeText(requireContext(),"구현 예정(데이터베이스필요)", Toast.LENGTH_LONG).show();
        });


        return view;
    }
}








