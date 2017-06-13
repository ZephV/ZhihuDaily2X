package com.zeph.zhihudaily2x.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zeph.zhihudaily2x.R;
import com.zeph.zhihudaily2x.adapter.ArticleAdapter;
import com.zeph.zhihudaily2x.bean.ArticleBean;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private String mType;
    private ListView mListViewNews;
    private ArticleAdapter mArticleAdapter;
    private HomeContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newslist_main, container, false);
        mListViewNews = (ListView) view.findViewById(R.id.lv_news);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new HomePresenter(this, getActivity());
        setPresenter(presenter);
    }

    public static HomeFragment getInstance(String type) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.mType = type;
        return homeFragment;
    }

    //下面是接口方法的重写


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        presenter.loadArticle(mType);
    }

    @Override
    public void showArticle(List<ArticleBean> articleBeenList) {
        mArticleAdapter = new ArticleAdapter(articleBeenList,getContext());
        mListViewNews.setAdapter(mArticleAdapter);
        mListViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent= new Intent(getActivity(),Ar)
            }
        });

    }
}
