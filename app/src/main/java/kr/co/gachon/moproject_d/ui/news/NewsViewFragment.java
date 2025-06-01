package kr.co.gachon.moproject_d.ui.news;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.bumptech.glide.Glide;
import org.jsoup.Jsoup;
import java.util.List;

import kr.co.gachon.moproject_d.R;
import kr.co.gachon.moproject_d.model.Word;
import kr.co.gachon.moproject_d.model.newsview.NewsViewResponse;
import kr.co.gachon.moproject_d.model.newsview.NewsViewWordResponse;
import kr.co.gachon.moproject_d.utils.WordManager;
import kr.co.gachon.moproject_d.utils.showSentencesInTextView;


public class NewsViewFragment extends Fragment {
    private WordManager wordManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordManager = new WordManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_view, container, false);

        Button btnBack = view.findViewById(R.id.btn_news_back);
        ImageButton btnWord = view.findViewById(R.id.btn_news_view_word);

        TextView txtNewsTitle = view.findViewById(R.id.txt_news_view_title);
        TextView txtNewsContent = view.findViewById(R.id.txt_news_view_content);
        TextView txtNewsDate = view.findViewById(R.id.txt_news_view_date);
        TextView txtNewsWord = view.findViewById(R.id.txt_news_view_word);
        TextView txtNewsWordDescription = view.findViewById(R.id.txt_news_view_description);

        ImageView imgNewsImg = view.findViewById(R.id.img_news_view_img);

        SharedPreferences prefs = requireContext().getSharedPreferences("userData",MODE_PRIVATE);

        btnBack.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });

        btnWord.setOnClickListener(v -> {
            Word newWord = new Word(txtNewsWord.getText().toString(),txtNewsWordDescription.getText().toString(),"English");
            wordManager.saveWord(newWord);
            Toast.makeText(requireContext(),"구현 예정(데이터베이스필요)", Toast.LENGTH_LONG).show();
        });


        NewsViewRepository repo = new NewsViewRepository();
        repo.fetchNews(Integer.parseInt(prefs.getString("index","Error")),new NewsViewRepository.NewsCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(NewsViewResponse response) {
                getActivity().runOnUiThread(() -> {
                    String firstTitle = response.article.title;
                    String firstDate = response.article.publishedAt;
                    String imageUrl = response.article.urlToImage;
                    String str1 = firstDate.substring(0,firstDate.indexOf("T"));
                    String str2 = firstDate.substring(firstDate.indexOf("T"),firstDate.indexOf("Z"));
                    List<String> firstContent = response.crawled.sentences;


                    Glide.with(view)
                            .load(imageUrl)
                            .fitCenter()
                            .into(imgNewsImg);

                    StringBuilder sb = new StringBuilder();


                    txtNewsTitle.setText(firstTitle);
                    showSentencesInTextView.displaySentences(txtNewsContent,firstContent);
                    txtNewsDate.setText(str1+str2);
                });
            }

            @Override
            public void onFailure(Exception e) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                });
            }
        });

        Handler handler = new Handler();
        final int[] lastStart = {-1};
        final int[] lastEnd = {-1};


        Runnable checkSelection = new Runnable() {
            @Override
            public void run() {
                int start = txtNewsContent.getSelectionStart();
                int end = txtNewsContent.getSelectionEnd();

                if (start >= 0 && end > start && (start != lastStart[0] || end != lastEnd[0])) {
                    String selectedText = txtNewsContent.getText().subSequence(start, end).toString();

                    lastStart[0] = start;
                    lastEnd[0] = end;

                    NewsViewWordRepository wordRepo = new NewsViewWordRepository();

                    wordRepo.fetchNews(selectedText,new NewsViewWordRepository.NewsCallback() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(NewsViewWordResponse response) {
                            getActivity().runOnUiThread(() -> {

                                if (response.result != null && !response.result.isEmpty()) {
                                    NewsViewWordResponse.WordTranslation firstResult = response.result.get(0);

                                    String speech = firstResult.partOfSpeech;
                                    String value = (firstResult.values != null && !firstResult.values.isEmpty())
                                            ? firstResult.values.get(0)
                                            : "";

                                    txtNewsWord.setText(selectedText);
                                    txtNewsWordDescription.setText(speech + ": " + Jsoup.parse(value).text());
                                } else {
                                    txtNewsWord.setText(selectedText);
                                    txtNewsWordDescription.setText("결과 없음");
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

                }
                handler.postDelayed(this, 100);
            }
        };

        handler.post(checkSelection);

        return view;
    }
}








