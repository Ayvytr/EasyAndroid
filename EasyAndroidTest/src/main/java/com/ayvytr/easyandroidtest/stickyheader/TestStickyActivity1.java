package com.ayvytr.easyandroidtest.stickyheader;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayvytr.easyandroid.tools.Convert;
import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroid.view.custom.CenterGravityTextView;
import com.ayvytr.easyandroidtest.R;
import com.ayvytr.easyandroidtest.stickyheader.itemdecoration.StickyItemHeaderDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestStickyActivity1 extends BaseEasyActivity
{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BasicAdapter basicAdapter;

    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        basicAdapter = new BasicAdapter();
        recyclerView.setAdapter(basicAdapter);
        recyclerView.addItemDecoration(new StickyItemHeaderDecoration(this));
    }

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_basic_sticky_header;
    }

    public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.Vh>
            implements StickyRecyclerHeadersAdapter<BasicAdapter.Vh>
    {
        List<String> list = new ArrayList<>();

        public BasicAdapter()
        {
            char c = 'a';
            for(int i = 0; i < 26; i++)
            {
                list.add(Convert.toString((char) (c + i)));
            }
        }

        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new Vh(LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.item_basic_sticky, parent, false));
        }

        @Override
        public void onBindViewHolder(Vh holder, int position)
        {
            holder.bind(position);
        }

        @Override
        public long getHeaderId(int position)
        {
            return position;
        }

        @Override
        public Vh onCreateHeaderViewHolder(ViewGroup parent)
        {
            return new Vh(LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.item_basic_sticky_header, parent, false));
        }

        @Override
        public void onBindHeaderViewHolder(Vh holder, int position)
        {
            holder.bind(position);
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        public class Vh extends RecyclerView.ViewHolder
        {
            @BindView(R.id.tv)
            CenterGravityTextView tv;

            public Vh(View view)
            {
                super(view);
                ButterKnife.bind(this, view);
            }

            public void bind(int position)
            {
                tv.setText(list.get(position));
            }
        }

    }
}
