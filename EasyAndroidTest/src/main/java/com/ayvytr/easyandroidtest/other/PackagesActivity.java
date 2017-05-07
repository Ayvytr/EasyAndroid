package com.ayvytr.easyandroidtest.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayvytr.easyandroid.bean.AppInfo;
import com.ayvytr.easyandroid.tools.withcontext.Packages;
import com.ayvytr.easyandroidtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class PackagesActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private PackageAdapter packageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        initView();
    }

    private void initView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        packageAdapter = new PackageAdapter();
        recyclerView.setAdapter(packageAdapter);
        packageAdapter.update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_packages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_id_all:
                packageAdapter.update();
                return true;
            case R.id.menu_id_system:
                packageAdapter.updateSystem();
                return true;
            case R.id.menu_id_user:
                packageAdapter.updateUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.Vh>
    {
        private List<AppInfo> list = new ArrayList<>();

        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new Vh(LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.item_packagtes, parent, false));
        }

        @Override
        public void onBindViewHolder(Vh holder, int position)
        {
            holder.update(position);
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        public void update()
        {
            list.clear();
            list.addAll(Packages.getInstalledAppsInfo());
            notifyDataSetChanged();
        }

        public void updateSystem()
        {
            List<AppInfo> appInfo = Packages.getInstalledAppsInfo();
            Observable.fromIterable(appInfo).filter(new Predicate<AppInfo>()
            {
                @Override
                public boolean test(AppInfo appInfo) throws Exception
                {
                    return appInfo.isSystemApp;
                }
            }).buffer(appInfo.size()).subscribe(new Consumer<List<AppInfo>>()
            {
                @Override
                public void accept(List<AppInfo> appInfos) throws Exception
                {
                    list.clear();
                    list.addAll(appInfos);
                    notifyDataSetChanged();
                }
            });

        }

        public void updateUser()
        {
            List<AppInfo> appInfo = Packages.getInstalledAppsInfo();
            Observable.fromIterable(appInfo).filter(new Predicate<AppInfo>()
            {
                @Override
                public boolean test(AppInfo appInfo) throws Exception
                {
                    return !appInfo.isSystemApp;
                }
            }).buffer(appInfo.size()).subscribe(new Consumer<List<AppInfo>>()
            {
                @Override
                public void accept(List<AppInfo> appInfos) throws Exception
                {
                    list.clear();
                    list.addAll(appInfos);
                    notifyDataSetChanged();
                }
            });
        }

        public class Vh extends RecyclerView.ViewHolder
        {
            @BindView(R.id.iv)
            ImageView iv;
            @BindView(R.id.tvTitle)
            TextView tvTitle;
            @BindView(R.id.tvPackageName)
            TextView tvPackageName;

            public Vh(View itemView)
            {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void update(int position)
            {
                AppInfo appInfo = list.get(position);
                tvTitle.setText(appInfo.label);
                appInfo.loadIconInto(iv);
                tvPackageName.setText(appInfo.packageName);
            }
        }

    }
}
