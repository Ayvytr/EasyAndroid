package com.ayvytr.easyandroid.view.recyclerview.itemdecoration.header;

import android.support.v7.widget.RecyclerView;

public interface StickyItemHeaderAdapter<VH extends RecyclerView.ViewHolder>
{
    int getId(int position);

    VH onCreateHeaderViewHolder(RecyclerView parent);

    void onBindHeaderViewHolder(VH holder, int position);
}
