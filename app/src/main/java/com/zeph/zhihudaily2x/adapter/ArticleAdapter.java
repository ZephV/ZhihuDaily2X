package com.zeph.zhihudaily2x.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeph.zhihudaily2x.R;
import com.zeph.zhihudaily2x.bean.ArticleBean;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ArticleBean> mNewsList;

    public ArticleAdapter(List<ArticleBean> newsList, Context context) {
        mNewsList = newsList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.news_item, null);
            viewHold = new ViewHold(convertView);
            convertView.setTag(viewHold);
        }
        viewHold = (ViewHold) convertView.getTag();
        ArticleBean news = mNewsList.get(position);
        ImageView ivNews = viewHold.ivNews;
        TextView tvNews = viewHold.tvNews;
        tvNews.setText(news.getTitle());
        if (news.getImage()==null){
            ivNews.setVisibility(View.GONE);
        }else {
            ivNews.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(news.getImage()).into(ivNews);
        }
        return convertView;
    }

    private class ViewHold {
        ImageView ivNews;
        TextView tvNews;

        public ViewHold(View v) {
            ivNews = (ImageView) v.findViewById(R.id.iv_news_item);
            tvNews = (TextView) v.findViewById(R.id.title_news_item);
        }
    }
}
