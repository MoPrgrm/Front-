package kr.co.gachon.moproject_d.ui.news;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import kr.co.gachon.moproject_d.model.newsview.NewsViewWordResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsViewWordRepository {

    public interface NewsCallback {
        void onSuccess(NewsViewWordResponse viewWordResponse);
        void onFailure(Exception e);
    }

    public void fetchNews(String word, NewsCallback callback) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://10.0.2.2:5000/api/dict?word=" + word;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    NewsViewWordResponse viewWordResponse = gson.fromJson(responseBody, NewsViewWordResponse.class);
                    callback.onSuccess(viewWordResponse);
                } else {
                    callback.onFailure(new IOException("응답 실패: " + response.code()));
                }
            }
        });
    }
}