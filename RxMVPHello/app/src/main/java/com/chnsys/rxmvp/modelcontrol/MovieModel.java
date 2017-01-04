package com.chnsys.rxmvp.modelcontrol;


import com.chnsys.rxmvp.Api.MovieServiceApi;
import com.chnsys.rxmvp.Api.OnHttpCallBack;
import com.chnsys.rxmvp.Api.RetrofitUtils;
import com.chnsys.rxmvp.bean.Movies;
import com.chnsys.rxmvp.constant.ApiConstant;


import com.socks.library.KLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 *具体的逻辑(业务)处理了忆
 *@author:juanqiang
 *created at:2016/12/30 下午2:40
 *
 */
public class MovieModel {

    public void getMovie(int start, int count, final OnHttpCallBack<Movies> callBack) {
        RetrofitUtils.newInstence(ApiConstant.MOVIE_TOP250_URL)
                .create(MovieServiceApi.class)
                .getMovies(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Movies>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Movies movies) {
                        callBack.onSuccessful(movies);//请求成功---回调
                        KLog.e(movies.toString());
                    }
                });
    }
}
