package com.faberventures.ivanbruel.testapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends BaseAdapter {

    private List<Location> mLocationList;
    private Activity mActivity;

    public MainAdapter(Activity activity, List<Location> locationList) {
        super();
        mActivity = activity;
        mLocationList = locationList;
    }

    @Override
    public int getCount() {
        return mLocationList.size();
    }

    @Override
    public Location getItem(int i) {
        return mLocationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = mActivity.getLayoutInflater();
        ItemViewHolder viewHolder = null;
        if (view != null) {
             viewHolder = (ItemViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.location_item_view, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        }

        Location location = getItem(i);
        viewHolder.mTextView.setText(location.getName());
        Picasso.with(mActivity).load(location.getImageURL()).into(viewHolder.mImageView);
        return view;
    }

    public class ItemViewHolder {

        public TextView mTextView;
        public ImageView mImageView;
        public ItemViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.textView);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
