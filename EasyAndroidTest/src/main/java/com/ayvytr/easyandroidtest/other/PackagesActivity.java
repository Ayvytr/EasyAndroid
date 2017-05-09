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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class PackagesActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private PackageAdapter packageAdapter;

    private int filterType = 0;

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
                filterType = 0;
                break;
            case R.id.menu_id_system:
                filterType = 1;
                break;
            case R.id.menu_id_user:
                filterType = 2;
                break;
        }
        packageAdapter.update();
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
            Observable
                    .create(new ObservableOnSubscribe<AppInfo>()
                    {
                        @Override
                        public void subscribe(ObservableEmitter<AppInfo> e) throws Exception
                        {
                            List<AppInfo> appInfo = Packages.getInstalledAppsInfo();
                            for(AppInfo a : appInfo)
                            {
                                e.onNext(a);
                            }
                            e.onComplete();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .filter(new Predicate<AppInfo>()
                    {
                        @Override
                        public boolean test(AppInfo appInfo) throws Exception
                        {
                            switch(filterType)
                            {
                                case 1:
                                    return appInfo.isSystemApp;
                                case 2:
                                    return !appInfo.isSystemApp;
                                default:
                                    return true;
                            }
                        }
                    })
                    .toSortedList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<AppInfo>>()
                    {
                        @Override
                        public void accept(List<AppInfo> appInfos) throws Exception
                        {
                            list = appInfos;
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
