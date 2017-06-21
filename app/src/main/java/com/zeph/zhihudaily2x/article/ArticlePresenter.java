package com.zeph.zhihudaily2x.article;


import com.zeph.zhihudaily2x.bean.ArticleDetailBean;
import com.zeph.zhihudaily2x.service.ActionService;
import com.zeph.zhihudaily2x.service.ServiceFactory;
import com.zeph.zhihudaily2x.util.HtmlUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ArticlePresenter implements ArticleContract.Presenter {

    private ArticleContract.View mView;
    private ActionService service;
    private CompositeSubscription subscription;



    public ArticlePresenter(ArticleContract.View view) {
        mView = view;
        service = ServiceFactory.createRetrofitService(ActionService.class, ActionService.baseUrl);
        subscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public void loadArticleDetail(int id) {
        int articleId = id;
        subscription.add(service.getNewsDetails(articleId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Func1<ArticleDetailBean, String>() {
            @Override
            public String call(ArticleDetailBean articleDetailBean) {
                return HtmlUtils.structHtml(articleDetailBean);
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
                mView.showArticleDetail(s);
            }
        }));
    }
}
