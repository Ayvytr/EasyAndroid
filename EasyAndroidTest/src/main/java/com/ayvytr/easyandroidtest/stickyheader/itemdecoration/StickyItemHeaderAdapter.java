package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface StickyItemHeaderAdapter<VH extends RecyclerView.ViewHolder>
{
    long getHeaderId(int position);

    VH onCreateHeaderViewHolder(ViewGroup parent);

    void onBindHeaderViewHolder(VH holder, int position);

    int getItemCount();
}
