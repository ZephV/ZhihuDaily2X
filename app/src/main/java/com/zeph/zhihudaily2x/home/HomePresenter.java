package com.zeph.zhihudaily2x.home;


import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zeph.zhihudaily2x.bean.OptionBean;
import com.zeph.zhihudaily2x.bean.ThemeBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private CompositeSubscription mSubscription;
    private OptionBean mOptionBean;
    private String mType;

    public HomePresenter(HomeContract.View view, Context context) {
        mView = view;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadArticle(String type) {
        mType = type;
        Observable<ThemeBean> rootBean = null;
        switch (mType) {
            case "电影日报":
                rootBean = mOptionBean.getMovieNews();
                break;
            case "设计日报":
                rootBean = mOptionBean.getDesignNews();
                break;
            case "动漫日报":
                rootBean = mOptionBean.getAnimeNews();
                break;
            case "游戏日报":
                rootBean = mOptionBean.getGameNews();
                break;
            case "互联网日报":
                rootBean = mOptionBean.getInterNetNews();
                break;
            default:
                break;
        }

        mSubscription.add(rootBean
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                    }

                    @Override
                    public void onNext(ThemeBean themeBean) {
                        mView.showArticle(themeBean.getNewsList());
                    }
                })
        );
    }
}
