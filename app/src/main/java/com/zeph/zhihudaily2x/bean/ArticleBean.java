package com.zeph.zhihudaily2x.bean;


import java.util.List;

public class ArticleBean {

  private String title;
  private int id;
  private List<String> image;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getImage() {
    return image;
  }

  public void setImage(List<String> image) {
    this.image = image;
  }
}
