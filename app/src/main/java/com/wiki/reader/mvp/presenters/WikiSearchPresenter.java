package com.wiki.reader.mvp.presenters;

import com.wiki.reader.db.WikiDatabaseService;
import com.wiki.reader.models.Page;
import com.wiki.reader.models.WikiSearchAPIResponse;
import com.wiki.reader.mvp.views.IWikiSearchView;
import com.wiki.reader.network.WikiSearchApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WikiSearchPresenter {

    WikiSearchApiInterface wikiSearchApiInterface;
    IWikiSearchView iWikiSearchView;

    @Inject
    WikiSearchPresenter(WikiSearchApiInterface wikiSearchApiInterface) {
        this.wikiSearchApiInterface = wikiSearchApiInterface;
    }

    public void setiWikiSearchView(IWikiSearchView iWikiSearchView) {
        this.iWikiSearchView = iWikiSearchView;
    }

    public void getQueriedWikiSearchResult(String query) {

        iWikiSearchView.showLoading();
        wikiSearchApiInterface.getWikiSearchResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WikiSearchAPIResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(WikiSearchAPIResponse wikiSearchAPIResponse) {
                        if (wikiSearchAPIResponse != null && wikiSearchAPIResponse.getQuery() != null
                                && wikiSearchAPIResponse.getQuery().getPages() != null
                                && !wikiSearchAPIResponse.getQuery().getPages().isEmpty()) {
                            List<Page> pageList = wikiSearchAPIResponse.getQuery().getPages();
                            iWikiSearchView.showWikiListing(pageList);
                            saveWikiPagesToLocal(pageList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iWikiSearchView.showErrorView();
                    }

                    @Override
                    public void onComplete() {
                        iWikiSearchView.hideLoading();
                    }
                });
    }

    private void saveWikiPagesToLocal(List<Page> pageList) {
        WikiDatabaseService.getInstance().addWikiPagesToLocal(pageList);
    }

    public void getQueriedWikiSearchFromLocal(String query) {
        iWikiSearchView.showLoading();
        Observable.just(WikiDatabaseService.getInstance().searchWikiPageFromLocal(query.toLowerCase()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Page>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Page> value) {
                        iWikiSearchView.showWikiListing(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iWikiSearchView.showErrorView();
                    }

                    @Override
                    public void onComplete() {
                        iWikiSearchView.hideLoading();
                    }
                });
    }

    public void closeLocalSource() {
        WikiDatabaseService.getInstance().closeRealm();
    }
}