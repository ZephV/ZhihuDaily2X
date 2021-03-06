package com.zeph.zhihudaily2x.home;


import android.content.Intent;
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
import com.zeph.zhihudaily2x.article.ArticleActivity;
import com.zeph.zhihudaily2x.bean.ArticleBean;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private String type;
    private ListView mListView;
    private View view;
    private ArticleAdapter mAadapter;
    private HomeContract.Presenter preseter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newslist_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initView(); // 初始化各控件
        preseter = new HomePresenter(this, getActivity());
        setPresenter(preseter);


    }

    // 根据选项重载HomeFragment
    public static HomeFragment getInstance(String type) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.type = type;
        return homeFragment;
    }

    private void initView() {
        mListView = (ListView) view.findViewById(R.id.lv_news);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        presenter.loadArticle(type);
    }

    @Override
    public void showArticle(final List<ArticleBean> articleBeanList) {
        mAadapter = new ArticleAdapter(articleBeanList, getContext());
        mListView.setAdapter(mAadapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent = intent.putExtra("id", articleBeanList.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
