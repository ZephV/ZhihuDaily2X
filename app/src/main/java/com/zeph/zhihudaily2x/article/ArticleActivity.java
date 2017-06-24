package com.zeph.zhihudaily2x.article;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.zeph.zhihudaily2x.R;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View {

    private WebView webView;
    private FloatingActionButton fab;
    private int mId;
    private ArticleContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
        fabSetOnClick();
//        //左上角出现小箭头
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        presenter = new ArticlePresenter(this, this);
        setPresenter(presenter);
        webView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    private void fabSetOnClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view);
        fab = (FloatingActionButton) findViewById(R.id.fab_article);
    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
        presenter.loadArticleDetail(mId);
    }

    @Override
    public void showArticleDetail(String content) {
        webView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "UTF-8", null);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //点击小箭头返回
//        if(item.getItemId() == android.R.id.home)
//        {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
