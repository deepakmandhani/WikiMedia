package com.news.reader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.news.reader.R;
import com.news.reader.activities.WebViewActivity;
import com.news.reader.models.Value;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListRecyclerViewAdapter extends RecyclerView.Adapter<NewsListRecyclerViewAdapter.NewsItemViewHolder> {

    public Context context;
    public List<Value> valueList;

    public NewsListRecyclerViewAdapter(Context context, List<Value> valueList) {
        this.context = context;
        this.valueList = valueList;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        final Value value = valueList.get(position);

        holder.newsItemPublisher.setText(value.getProvider() != null ? Html.fromHtml("Publisher: <b>" + value.getProvider().getName() + "</b>") : "");
        holder.newsItemTitle.setText(Html.fromHtml(value.getTitle()));

        if (value.getImage() != null && value.getImage().getUrl() != null && !value.getImage().getUrl().isEmpty()) {
            Picasso.get().load(value.getImage().getUrl())
                    .placeholder(R.drawable.place_holder_ic)
                    .error(R.drawable.place_holder_ic)
                    .into(holder.newsItemImageView);
        } else {
            Picasso.get().load(R.drawable.place_holder_ic)
                    .into(holder.newsItemImageView);
        }

        holder.newsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(WebViewActivity.NEWS_DATA, value.getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return valueList != null ? valueList.size() : 0;
    }

    public void updateNewsList(List<Value> list) {
        if (!valueList.isEmpty()) {
            valueList.clear();
            valueList.addAll(list);
        } else {
            valueList = list;
        }
        notifyDataSetChanged();
    }

    public static class NewsItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_item_ic)
        ImageView newsItemImageView;

        @BindView(R.id.news_item_title)
        TextView newsItemTitle;

        @BindView(R.id.news_item_publisher)
        TextView newsItemPublisher;

        @BindView(R.id.news_list_item_view)
        RelativeLayout newsItemView;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
