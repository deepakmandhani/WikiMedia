package com.wiki.reader.adapters;

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

import com.wiki.reader.R;
import com.wiki.reader.activities.WebViewActivity;
import com.wiki.reader.models.Page;
import com.wiki.reader.models.Terms;
import com.wiki.reader.models.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiListRecyclerViewAdapter extends RecyclerView.Adapter<WikiListRecyclerViewAdapter.WikiItemViewHolder> {

    public Context context;
    public List<Page> pageList;

    public WikiListRecyclerViewAdapter(Context context, List<Page> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @Override
    public WikiItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wiki_list_item, parent, false);
        return new WikiItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WikiItemViewHolder holder, int position) {
        final Page value = pageList.get(position);

        Terms terms = value.getTerms();
        if(terms != null && terms.getDescription() != null && !terms.getDescription().isEmpty())
        holder.newsItemPublisher.setText(terms.getDescription().get(0));
        holder.newsItemTitle.setText(Html.fromHtml(value.getTitle()));

        Thumbnail thumbnail = value.getThumbnail();
        if (thumbnail != null && thumbnail.getSource() != null) {
            Picasso.get().load(thumbnail.getSource())
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
                //todo -- send respective page in details once avaulavle in api response
                intent.putExtra(WebViewActivity.WIKI_DATA, "URL needed here");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageList != null ? pageList.size() : 0;
    }

    public void updateNewsList(List<Page> list) {
        if (!pageList.isEmpty()) {
            pageList.clear();
            pageList.addAll(list);
        } else {
            pageList = list;
        }
        notifyDataSetChanged();
    }

    public static class WikiItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_item_ic)
        ImageView newsItemImageView;

        @BindView(R.id.news_item_title)
        TextView newsItemTitle;

        @BindView(R.id.news_item_publisher)
        TextView newsItemPublisher;

        @BindView(R.id.news_list_item_view)
        RelativeLayout newsItemView;

        public WikiItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
