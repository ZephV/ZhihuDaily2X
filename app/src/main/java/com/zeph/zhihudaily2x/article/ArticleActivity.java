package com.zeph.zhihudaily2x.article;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.zeph.zhihudaily2x.R;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View {

    private WebView webView;
    private int mId;
    private ArticleContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        presenter = new ArticlePresenter(this, this);
        setPresenter(presenter);

    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view);
    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
        presenter.loadArticleDetail(mId);
    }

    @Override
    public void showArticleDetail(String content) {
        webView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "UTF-8", null);
    }
}
