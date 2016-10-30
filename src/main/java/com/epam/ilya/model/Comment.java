package com.epam.ilya.model;

import org.joda.time.DateTime;

public class Comment extends BaseEntity {
    private String author;
    private DateTime date;
    private String content;
    private News news;

    public Comment() {
        this.date = DateTime.now();
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
