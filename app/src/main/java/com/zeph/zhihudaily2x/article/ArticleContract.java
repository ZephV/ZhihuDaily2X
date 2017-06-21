package com.zeph.zhihudaily2x.article;


import com.zeph.zhihudaily2x.BasePresenter;
import com.zeph.zhihudaily2x.BaseView;

public interface ArticleContract {

    interface Presenter extends BasePresenter {
        void loadArticleDetail(int id); // 加载文章的详细内容
    }

    interface View extends BaseView<Presenter> {
        void showArticleDetail(String content); // 显示文章的详细内容
    }


}
