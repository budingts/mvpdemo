package com.chnsys.rxmvp.view;

import android.content.Context;

import java.util.List;

import com.chnsys.rxmvp.bean.Movies;

/**
 *  V层
 *
 * @author juanqiang  Create at: 2016/12/30  下午1:53
 */

public interface IMovieView {

    void showBottom(int lastIndex);

    Context getCurContext();//获取上下文对象

    void showProgress();//显示进度条

    void hideProgress();//隐藏进度条

    void showData(List<Movies.SubjectsBean> movies);//显示数据到View上

    void showInfo(String info);//提示用户,提升友好交互

}
