package com.zeph.zhihudaily2x.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View{

    private WebView mWebView;
    private int mId;
    private ArticleContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
    }

    private void initParms(Bundle bundle) {
        Intent intent = getIntent();
        mId =intent.getIntExtra("id",0);
    }


    // 下面是接口方法的重写

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
        presenter.loadArticleDetail(mId);
    }

    @Override
    public void showArticleDetail(String content) {
        mWebView.loadDataWithBaseURL("file:///android_asset",content,"text/html","UTF-8",null);
    }
}
