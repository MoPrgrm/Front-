package kr.co.gachon.moproject_d.ui.news;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import kr.co.gachon.moproject_d.model.newsview.NewsViewResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsViewRepository {

    public interface NewsCallback {
        void onSuccess(NewsViewResponse viewResponse);
        void onFailure(Exception e);
    }

    public void fetchNews(int id, NewsCallback callback) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://10.0.2.2:5000/api/news/" + id;

        Request request = new Request.Builder()
                .url(url)  // Flask API 주소
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                callback.onFailure(e);  // 실패 콜백
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    NewsViewResponse viewResponse = gson.fromJson(responseBody, NewsViewResponse.class);
                    callback.onSuccess(viewResponse);  // 성공 콜백
                } else {
                    callback.onFailure(new IOException("응답 실패: " + response.code()));
                }
            }
        });
    }
}