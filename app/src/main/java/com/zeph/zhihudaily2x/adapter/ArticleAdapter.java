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

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {

    private ArrayList<ArticleBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ArticleAdapter(ArrayList<ArticleBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        TextView title = viewHolder.vh_title;
        ImageView image = viewHolder.vh_image;
        ArticleBean bean = mList.get(position);
        title.setText(bean.getTitle());
        if (bean.getImages()==null){
            image.setVisibility(View.GONE);
        }
        else{
            image.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(bean.getImages()).into(image);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView vh_title;
        ImageView vh_image;

        public ViewHolder(View v) {
            vh_title = (TextView) v.findViewById(R.id.title_news_item);
            vh_image = (ImageView) v.findViewById(R.id.iv_news_item);
        }
    }
}
