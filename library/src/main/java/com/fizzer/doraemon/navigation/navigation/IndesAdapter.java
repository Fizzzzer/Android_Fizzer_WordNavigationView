package com.fizzer.doraemon.navigation.navigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.fizzer.doraemon.library.R;

import java.util.List;

/**
 * Created by Fizzer on 2017/2/22.
 * Email: doraemonmqq@sina.com
 */

public class IndesAdapter extends BaseAdapter {
    private List<NavigationBean> mList;
    private Context mContext;

    public IndesAdapter(Context context,List<NavigationBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;

        if(convertView == null){
            mViewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.view_index_item,null);
            mViewHolder.tvHeader = (TextView) convertView.findViewById(R.id.tvHeader);
            mViewHolder.tvName = (TextView) convertView.findViewById(R.id.tvTextName);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if(position != 0 && mList.get(position).headChar.equals(mList.get(position-1).headChar)){
            mViewHolder.tvHeader.setVisibility(View.GONE);
        }else{
            mViewHolder.tvHeader.setVisibility(View.VISIBLE);
            mViewHolder.tvHeader.setText(mList.get(position).headChar);
        }

        mViewHolder.tvName.setText(mList.get(position).name);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView tvHeader;
        TextView tvName;
    }

}
