package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.support.v7.widget.RecyclerView;

public interface StickyItemHeaderAdapter<VH extends RecyclerView.ViewHolder>
{
    boolean isHeader(int position);

    int getId(int position);

    VH onCreateHeaderViewHolder(RecyclerView parent);

    void onBindHeaderViewHolder(VH holder, int position);
}
