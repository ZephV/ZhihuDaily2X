package com.zeph.zhihudaily2x.article;


import android.content.Context;

import com.zeph.zhihudaily2x.bean.ArticleDetailBean;
import com.zeph.zhihudaily2x.bean.OptionBean;
import com.zeph.zhihudaily2x.util.HtmlUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ArticlePresenter implements ArticleContract.Presenter {

    private CompositeSubscription subscription;
    private ArticleContract.View view;
    private OptionBean bean;
    private int mId;

    public ArticlePresenter(ArticleContract.View view, Context context) {
        this.view = view;
        //此处添加缓存功能
        subscription=new CompositeSubscription();
    }



    @Override
    public void subscribe() {
        if (mId!=0){
            loadArticleDetail(mId);
        }
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }

    @Override
    public void loadArticleDetail(int id) {
        mId = id;
        subscription.add(bean.getNewsDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Func1<ArticleDetailBean, String>() {
            @Override
            public String call(ArticleDetailBean articleDetailBean) {
                return HtmlUtil.structHtml(articleDetailBean);
            }
        })
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                view.showArticleDetail(s);
            }
        }));
    }
}
