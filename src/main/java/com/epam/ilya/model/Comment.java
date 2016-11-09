package com.epam.ilya.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Class describes explanation or guest's thoughts about {@link News}
 *
 * @author Ilya_Bondarenko
 */


@Entity
@Table(name = "\"COMMENT\"")
public class Comment extends BaseEntity implements Serializable {

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "\"date\"")
    private Date date;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NEWS_ID" )
    private int newsId;

    @ManyToOne
    @JoinColumn(name = "NEWS_ID" ,insertable = false,updatable = false , nullable = false)
    private News news;

    public Comment() {
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                "author='" + author + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", news=" + news +
                '}';
    }
}
