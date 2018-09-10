package com.wiki.reader.mvp.views;

import com.wiki.reader.models.Page;

import java.util.List;

public interface IWikiSearchView {

    void showLoading();
    void hideLoading();
    void showWikiListing(List<Page> pageList);
    void showErrorView();
}
