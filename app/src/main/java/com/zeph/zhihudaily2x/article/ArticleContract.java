package com.zeph.zhihudaily2x.article;


import com.zeph.zhihudaily2x.BasePresenter;
import com.zeph.zhihudaily2x.BaseView;

public interface ArticleContract {

  interface View extends BaseView<Presenter> {

    void showArticleDetail(String content);

  }

  interface Presenter extends BasePresenter {

    void loadArticleDetail(int id);

  }


}
