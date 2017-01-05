package com.harambe.refresherauto;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sandeep on 05-01-2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    ArrayList<DateItem> myList=new ArrayList<>();

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new MainViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.mTextItem.setText(myList.get(position).toString());
        if(!myList.get(position).highlighted) {
            myList.get(position).highlighted=true;
            holder.mLineLayout.setBackgroundResource(R.drawable.highlight);
            TransitionDrawable trans = (TransitionDrawable) holder.mLineLayout.getBackground();
            trans.startTransition(2000);
        }
    }

    @Override
    public void onViewRecycled(MainViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mLineLayout.setBackgroundResource(R.drawable.normal_state);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void addDateItem(DateItem dateItem) {
        myList.add(0,dateItem);
        notifyItemInserted(0);
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rcv_item_tv)
        TextView mTextItem;
        @BindView(R.id.ll_item)
        LinearLayout mLineLayout;
        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
