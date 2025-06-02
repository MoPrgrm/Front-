package kr.co.gachon.moproject_d.model.newsview;

import java.util.List;

public class NewsViewResponse {
    public Article article;
    public String category;
    public Crawled crawled;
    public String source;
    public String status;
    public List<Translation> translations;
}

