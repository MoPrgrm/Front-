package kr.co.gachon.moproject_d.ui.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;

import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.model.NewsResponse;


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

        TextView txtNewsTitle1 = view.findViewById(R.id.txt_news_list_title_1);
        TextView txtNewsDate1 = view.findViewById(R.id.txt_news_list_date_1);

        ImageView imgNewsImage1 = view.findViewById(R.id.img_news_list_1);

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });


        btnNewsList1.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });

        NewsRepository repo = new NewsRepository();
        repo.fetchNews(new NewsRepository.NewsCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(NewsResponse response) {
                getActivity().runOnUiThread(() -> {
                    String firstTitle = response.articles.get(0).title;
                    String firstDate = response.articles.get(0).publishedAt;
                    String imageUrl = response.articles.get(0).urlToImage;
                    String str1 = firstDate.substring(0,firstDate.indexOf("T"));
                    String str2 = firstDate.substring(firstDate.indexOf("T"),firstDate.indexOf("."));



                    Glide.with(view)
                            .load(imageUrl)
                            .override(100, 100)
                            .centerCrop()
                            .into(imgNewsImage1);

                    txtNewsTitle1.setText(firstTitle);
                    txtNewsDate1.setText(str1+str2);
                    Log.d("뉴스 제목", firstTitle);
                });
            }

            @Override
            public void onFailure(Exception e) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                });
            }
        });


        return view;
    }
}
