package com.zeph.zhihudaily2x.bean;


import retrofit2.http.GET;
import rx.Observable;

public interface OptionBean {

  public static String baseUrl = "http://news-at.zhihu.com";

  // 电影日报3
  @GET("/api/4/theme/3")
  Observable<ThemeBean> getMovieNews();

  // 设计4
  @GET("/api/4/theme/4")
  Observable<ThemeBean> getDesignNews();

  // 动漫10
  @GET("/api/4/theme/10")
  Observable<ThemeBean> getAnimeNews();

  // 游戏8
  @GET("/api/4/theme/8")
  Observable<ThemeBean> getGameNews();

  // 互联网7
  @GET("/api/4/theme/7")
  Observable<ThemeBean> getInterNetNews();

}
