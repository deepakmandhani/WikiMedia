package com.news.reader.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.news.reader.NewsSearchApplication;
import com.news.reader.R;
import com.news.reader.adapters.NewsListRecyclerViewAdapter;
import com.news.reader.models.Value;
import com.news.reader.mvp.presenters.NewsSearchPresenter;
import com.news.reader.mvp.views.INewsSearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Optional;

public class NewsSearchActivity extends AppCompatActivity implements INewsSearchView {

    @Inject
    NewsSearchPresenter newsSearchPresenter;

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

    public NewsListRecyclerViewAdapter newsListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_search);
        ButterKnife.bind(this);
        initDependecies();
        initRecycler();
        newsSearchPresenter.setiNewsSearchView(this);
    }

    private void initDependecies() {
        ((NewsSearchApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void initRecycler() {
        newsListRecyclerViewAdapter = new NewsListRecyclerViewAdapter(this, new ArrayList<Value>());
        newsSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        newsSearchRecyclerView.setAdapter(newsListRecyclerViewAdapter);
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
    public void showNewsListing(List<Value> valueList) {
        newsListRecyclerViewAdapter.updateNewsList(valueList);
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
        newsSearchPresenter.getQueriedNewsSearchResult(charSequence.toString());
    }

}
