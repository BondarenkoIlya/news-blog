package com.epam.ilya.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class NewsTest {

    private static final String BREAKING_NEWS_TRUMP_WINS = "Breaking news: Trump wins";

    @Test
    public void shouldReturnSpecifiedTitleWhenSetIt() throws Exception {
        // given
        News item = new News();
        // when
        item.setTitle(BREAKING_NEWS_TRUMP_WINS);
        // then
        assertThat(item.getTitle(), is(BREAKING_NEWS_TRUMP_WINS)); // hamcrest
    }

    @Test
    public void shouldReturnAllInformationAboutNewsWhenToStringIsCalled() throws Exception {


    }
}
