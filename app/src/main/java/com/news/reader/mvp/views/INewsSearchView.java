package com.news.reader.mvp.views;

import com.news.reader.models.Value;

import java.util.List;

public interface INewsSearchView {

    void showLoading();
    void hideLoading();
    void showNewsListing(List<Value> valueList);
    void showErrorView();
}
