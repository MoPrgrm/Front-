package kr.co.gachon.moproject_d.ui.news;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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

import java.util.Objects;

import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.model.newslist.NewsResponse;


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
        Button btnNewsList2 = view.findViewById(R.id.btn_news_list_2);
        Button btnNewsList3 = view.findViewById(R.id.btn_news_list_3);
        Button btnNewsList4 = view.findViewById(R.id.btn_news_list_4);
        Button btnNewsList5 = view.findViewById(R.id.btn_news_list_5);
        Button btnNewsList6 = view.findViewById(R.id.btn_news_list_6);
        Button btnBack = view.findViewById(R.id.btn_news_back);

        TextView txtNewsTitle1 = view.findViewById(R.id.txt_news_list_title_1);
        TextView txtNewsTitle2 = view.findViewById(R.id.txt_news_list_title_2);
        TextView txtNewsTitle3 = view.findViewById(R.id.txt_news_list_title_3);
        TextView txtNewsTitle4 = view.findViewById(R.id.txt_news_list_title_4);
        TextView txtNewsTitle5 = view.findViewById(R.id.txt_news_list_title_5);
        TextView txtNewsTitle6 = view.findViewById(R.id.txt_news_list_title_6);

        TextView txtNewsDate1 = view.findViewById(R.id.txt_news_list_date_1);
        TextView txtNewsDate2 = view.findViewById(R.id.txt_news_list_date_2);
        TextView txtNewsDate3 = view.findViewById(R.id.txt_news_list_date_3);
        TextView txtNewsDate4 = view.findViewById(R.id.txt_news_list_date_4);
        TextView txtNewsDate5 = view.findViewById(R.id.txt_news_list_date_5);
        TextView txtNewsDate6 = view.findViewById(R.id.txt_news_list_date_6);

        ImageView imgNewsImage1 = view.findViewById(R.id.img_news_list_1);
        ImageView imgNewsImage2 = view.findViewById(R.id.img_news_list_2);
        ImageView imgNewsImage3 = view.findViewById(R.id.img_news_list_3);
        ImageView imgNewsImage4 = view.findViewById(R.id.img_news_list_4);
        ImageView imgNewsImage5 = view.findViewById(R.id.img_news_list_5);
        ImageView imgNewsImage6 = view.findViewById(R.id.img_news_list_6);

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });
        SharedPreferences prefs = requireContext().getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        btnNewsList1.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list1","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });
        btnNewsList2.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list2","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });
        btnNewsList3.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list3","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });
        btnNewsList4.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list4","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });
        btnNewsList5.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list5","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });
        btnNewsList6.setOnClickListener(v -> {
            editor.putString("index",prefs.getString("list6","Error"));
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_news_list_to_view);
        });


        NewsRepository repo = new NewsRepository();

        repo.fetchNews(new NewsRepository.NewsCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(NewsResponse response) {
                getActivity().runOnUiThread(() -> {
                    int count = 0;
                    String date,str1,str2;
                    for(int i =0; i<20; i++){
                        Log.d("test", String.valueOf(i));
                        if(Objects.equals(response.articles.get(i).source.id, prefs.getString("site", null))){
                            Log.d("test", String.valueOf(i));
                            count +=1;
                            switch (count){
                                case 1:
                                    btnNewsList1.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    Log.d("test",date);
                                    str1 = date.substring(0,date.indexOf("T"));
                                    Log.d("test","hihi");
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage1);

                                    txtNewsTitle1.setText(response.articles.get(i).title);
                                    txtNewsDate1.setText(str1+str2);

                                    //임시방편으로 shared로했습니다.

                                    editor.putString("list1", String.valueOf(i));
                                    editor.apply();

                                    break;
                                case 2:
                                    btnNewsList2.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    str1 = date.substring(0,date.indexOf("T"));
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage2);

                                    txtNewsTitle2.setText(response.articles.get(i).title);
                                    txtNewsDate2.setText(str1+str2);

                                    editor.putString("list2", String.valueOf(i));
                                    editor.apply();

                                    break;
                                case 3:
                                    btnNewsList3.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    str1 = date.substring(0,date.indexOf("T"));
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage3);

                                    txtNewsTitle3.setText(response.articles.get(i).title);
                                    txtNewsDate3.setText(str1+str2);

                                    editor.putString("list3", String.valueOf(i));
                                    editor.apply();

                                    break;
                                case 4:
                                    btnNewsList4.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    str1 = date.substring(0,date.indexOf("T"));
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage4);

                                    txtNewsTitle4.setText(response.articles.get(i).title);
                                    txtNewsDate4.setText(str1+str2);

                                    editor.putString("list4", String.valueOf(i));
                                    editor.apply();

                                    break;
                                case 5:
                                    btnNewsList5.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    str1 = date.substring(0,date.indexOf("T"));
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage5);

                                    txtNewsTitle5.setText(response.articles.get(i).title);
                                    txtNewsDate5.setText(str1+str2);

                                    editor.putString("list5", String.valueOf(i));
                                    editor.apply();

                                    break;
                                case 6:
                                    btnNewsList6.setEnabled(true);
                                    date = response.articles.get(i).publishedAt;
                                    str1 = date.substring(0,date.indexOf("T"));
                                    str2 = date.substring(date.indexOf("T"),date.indexOf("Z"));

                                    Glide.with(view)
                                            .load(response.articles.get(i).urlToImage)
                                            .fitCenter()
                                            .into(imgNewsImage6);

                                    txtNewsTitle6.setText(response.articles.get(i).title);
                                    txtNewsDate6.setText(str1+str2);

                                    editor.putString("list6", String.valueOf(i));
                                    editor.apply();

                                    break;
                            }
                        }
                    }
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
