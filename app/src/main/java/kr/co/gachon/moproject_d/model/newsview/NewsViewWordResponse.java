package kr.co.gachon.moproject_d.model.newsview;

import java.util.List;

public class NewsViewWordResponse {
    public String status;
    public String word;
    public List<WordTranslation> result;

    public static class WordTranslation {
        public String partOfSpeech;
        public List<String> values;

    }
}
