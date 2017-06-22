package com.zeph.zhihudaily2x.service;


import android.content.Context;

import com.zeph.zhihudaily2x.util.NetUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    // 创建Retrofit网络请求
    public static <T> T createRetrofitService(Class<T> tClass, String baseUrl
            , final Context context) {
        //设置缓存目录
        File cacheFile= new File(context.getExternalCacheDir(),"file");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置缓存大小
        okhttp3.Cache cache = new okhttp3.Cache(cacheFile,1024 * 1024 * 50);
        Interceptor internalCache = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //如果网络没有连接则设为强制缓存
                if (!NetUtils.isConnected(context)){
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetUtils.isConnected(context)) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        T service = retrofit.create(tClass);
        return service;

    }
}
