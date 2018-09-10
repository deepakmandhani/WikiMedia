package com.wiki.reader.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.wiki.reader.WikiSearchApplication;
import com.wiki.reader.R;
import com.wiki.reader.adapters.WikiListRecyclerViewAdapter;
import com.wiki.reader.models.Page;
import com.wiki.reader.mvp.presenters.WikiSearchPresenter;
import com.wiki.reader.mvp.views.IWikiSearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Optional;
import butterknife.Unbinder;

public class WikiSearchActivity extends AppCompatActivity implements IWikiSearchView {

    @Inject
    WikiSearchPresenter wikiSearchPresenter;

    @Nullable
    @BindView(R.id.news_search_et)
    EditText newSearchEditText;

    @Nullable
    @BindView(R.id.news_search_rv)
    RecyclerView newsSearchRecyclerView;

    @Nullable
    @BindView(R.id.news_search_pb)
    ProgressBar newsSearchProgressBar;

    @BindView(R.id.news_search_no_result)
    ImageView newSearchErrorText;

    Unbinder unbinder;

    public WikiListRecyclerViewAdapter wikiListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_search);
        unbinder = ButterKnife.bind(this);
        initDependecies();
        initRecycler();
        wikiSearchPresenter.setiWikiSearchView(this);
    }

    private void initDependecies() {
        ((WikiSearchApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void initRecycler() {
        wikiListRecyclerViewAdapter = new WikiListRecyclerViewAdapter(this, new ArrayList<Page>());
        newsSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        newsSearchRecyclerView.setAdapter(wikiListRecyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        wikiSearchPresenter.closeLocalSource();
    }

    @Override
    public void showLoading() {
        newsSearchProgressBar.setVisibility(View.VISIBLE);
        newsSearchRecyclerView.setVisibility(View.GONE);
        newSearchErrorText.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        newsSearchProgressBar.setVisibility(View.GONE);
        newsSearchRecyclerView.setVisibility(View.VISIBLE);
        newSearchErrorText.setVisibility(View.GONE);
    }

    @Override
    public void showWikiListing(List<Page> pageList) {
        wikiListRecyclerViewAdapter.updateNewsList(pageList);
    }

    @Override
    public void showErrorView() {
        newsSearchProgressBar.setVisibility(View.GONE);
        newsSearchRecyclerView.setVisibility(View.GONE);
        newSearchErrorText.setVisibility(View.VISIBLE);
    }

    @Optional
    @OnTextChanged(value = R.id.news_search_et, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onSearchTextChange(CharSequence charSequence) {
        if(isNetworkConeected()) {
            wikiSearchPresenter.getQueriedWikiSearchResult(charSequence.toString());
        } else {
            wikiSearchPresenter.getQueriedWikiSearchFromLocal(charSequence.toString());
        }
    }

    private boolean isNetworkConeected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
