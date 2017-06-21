package com.zeph.zhihudaily2x.service;


import com.zeph.zhihudaily2x.bean.ArticleDetailBean;
import com.zeph.zhihudaily2x.bean.RootBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ActionService {

    public static String baseUrl = "http://news-at.zhihu.com";

    //今日头条
    @GET("/api/4/news/latest")
    Observable<RootBean> getLatestNews();

    //互联网安全
    @GET("/api/4/theme/10")
    Observable<RootBean> getSafety();

    //不准无聊
    @GET("/api/4/theme/11")
    Observable<RootBean> getInterest();

    //体育日报
    @GET("/api/4/theme/8")
    Observable<RootBean> getSport();

    //传入id查看详细信息
    @GET("/api/4/news/{id}")
    Observable<ArticleDetailBean> getNewsDetails(@Path("id") int id);
}
