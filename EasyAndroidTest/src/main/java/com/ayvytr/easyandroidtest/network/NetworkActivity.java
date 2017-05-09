package com.ayvytr.easyandroidtest.network;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ayvytr.easyandroid.tools.withcontext.ToastTool;
import com.ayvytr.easyandroid.view.ViewTool;
import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroid.view.custom.LeftCenterGravityTextView;
import com.ayvytr.easyandroidtest.R;
import com.ayvytr.logger.L;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class NetworkActivity extends BaseEasyActivity
{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_network;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.update();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Vh>
    {
        List<News.ContentBean> list = new ArrayList<>();

        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new Vh(ViewTool.inflate2(R.layout.item_news, parent));
        }

        @Override
        public void onBindViewHolder(Vh holder, int position)
        {
            holder.bind(position);
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        public void update()
        {
            Network.getNews(new SingleObserver<List<News.ContentBean>>()
            {
                @Override
                public void onSubscribe(Disposable d)
                {
                    L.e();
                }

                @Override
                public void onSuccess(List<News.ContentBean> value)
                {
                    L.e();
                    list = value;
                    notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable e)
                {
                    L.e();
                }
            });
        }

        public class Vh extends RecyclerView.ViewHolder
        {
            @BindView(R.id.iv)
            ImageView iv;
            @BindView(R.id.tvTitle)
            LeftCenterGravityTextView tvTitle;
            @BindView(R.id.tvDigest)
            LeftCenterGravityTextView tvDigest;
            private View view;

            public Vh(View view)
            {
                super(view);
                this.view = view;
                ButterKnife.bind(this, view);
            }

            public void bind(int position)
            {
                News.ContentBean c = list.get(position);
                Glide.with(getContext())
                     .load(c.getImgsrc())
                     .into(iv);

                tvTitle.setText(c.getTitle());
                tvDigest.setText(c.getDigest());

                view.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        ToastTool.show("点击了");
                    }
                });
            }
        }

    }
}
