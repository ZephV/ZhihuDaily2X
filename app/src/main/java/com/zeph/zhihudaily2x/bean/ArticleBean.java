package com.zeph.zhihudaily2x.bean;


import java.util.List;

public class ArticleBean {

  private String image;
  private int id;
  private List<String> title;

  public ArticleBean(String image, int id, List<String> title) {
    this.image = image;
    this.id = id;
    this.title = title;
  }
}
