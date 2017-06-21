package com.zeph.zhihudaily2x.home;


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

    public HomePresenter(HomeContract.View view) {
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
    public void loadArticle(String type) {
        String mType = type;
        Observable<RootBean> rootBeanObservable = null;
        if (mType.equals("今日头条")) {
            rootBeanObservable = service.getLatestNews();
        }
        if (mType.equals("互联网安全")) {
            rootBeanObservable = service.getInterest();
        }
        if (mType.equals("不准无聊")) {
            rootBeanObservable = service.getInterest();
        }
        if (mType.equals("体育日报")) {
            rootBeanObservable = service.getInterest();
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
