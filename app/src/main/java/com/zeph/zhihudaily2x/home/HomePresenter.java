package com.zeph.zhihudaily2x.home;


import android.content.Context;

import com.zeph.zhihudaily2x.bean.RootBean;
import com.zeph.zhihudaily2x.service.ActionService;
import com.zeph.zhihudaily2x.service.ServiceFactory;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private ActionService service;
    private CompositeSubscription subscription;

    public HomePresenter(HomeContract.View view, Context context) {
        mView = view;
        service = ServiceFactory.createRetrofitService(ActionService.class, ActionService.baseUrl, context);
        subscription = new CompositeSubscription();
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadArticle(String type) {
        String mType = type;
        Observable<RootBean> rootBeanObservable = null;
        if (mType.equals("今日头条")) {
            rootBeanObservable = service.getLatestNews();
        }
        if (mType.equals("热门文章")) {
            rootBeanObservable = service.getHot();
        }
        if (mType.equals("用户推荐")) {
            rootBeanObservable = service.getUser();
        }
        if (mType.equals("电影日报")) {
            rootBeanObservable = service.getMovie();
        }
        if (mType.equals("网络安全")) {
            rootBeanObservable = service.getSafety();
        }
        if (mType.equals("体育日报")) {
            rootBeanObservable = service.getSport();
        }

        subscription.add(rootBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RootBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RootBean rootBean) {
                        mView.showArticle(rootBean.getStories());
                    }
                }));
    }
}
