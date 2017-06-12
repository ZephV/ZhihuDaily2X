package com.zeph.zhihudaily2x.home;


import android.widget.ListView;

import com.zeph.zhihudaily2x.adapter.ArticleAdapter;
import com.zeph.zhihudaily2x.bean.ArticleBean;

import java.util.List;

public class HomeFragment implements HomeContract.View {

    private String mType;
    private ListView mListViewNews;
    private ArticleAdapter mArticleAdapter;


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        presenter.loadArticle(mType);
    }

    @Override
    public void showArticle(List<ArticleBean> articleBeenList) {

    }
}
