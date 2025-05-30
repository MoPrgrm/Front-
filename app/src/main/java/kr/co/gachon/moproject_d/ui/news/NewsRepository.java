package kr.co.gachon.moproject_d.ui.news;

import com.google.gson.Gson;

import java.io.IOException;

import kr.co.gachon.moproject_d.model.NewsResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsRepository {

    public interface NewsCallback {
        void onSuccess(NewsResponse response);
        void onFailure(Exception e);
    }

    public void fetchNews(NewsCallback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/api/news")  // Flask API 주소
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);  // 실패 콜백
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    NewsResponse newsResponse = gson.fromJson(responseBody, NewsResponse.class);
                    callback.onSuccess(newsResponse);  // 성공 콜백
                } else {
                    callback.onFailure(new IOException("응답 실패: " + response.code()));
                }
            }
        });
    }
}