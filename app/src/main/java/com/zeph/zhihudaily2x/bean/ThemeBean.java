package com.zeph.zhihudaily2x.bean;


import java.util.List;

public class ThemeBean {

  private List<ArticleBean> NewsList;

  public List<ArticleBean> getNewsList() {
    return NewsList;
  }

  public void setNewsList(List<ArticleBean> newsList) {
    NewsList = newsList;
  }
}
