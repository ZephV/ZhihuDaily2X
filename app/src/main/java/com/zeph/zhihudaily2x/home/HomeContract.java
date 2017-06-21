package com.zeph.zhihudaily2x.home;


import com.zeph.zhihudaily2x.BasePresenter;
import com.zeph.zhihudaily2x.BaseView;
import com.zeph.zhihudaily2x.bean.ArticleBean;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showArticle(List<ArticleBean> articleBeanList); // 显示文章
    }

    interface Presenter extends BasePresenter {

        void loadArticle(String type); // 加载文章
    }
}
