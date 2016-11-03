package com.epam.ilya.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class News extends BaseEntity {
    private String title;
    private DateTime date;
    private String brief;
    private String content;
    private List<Comment> comments = new ArrayList<>();

    public News() {
        this.date = DateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
