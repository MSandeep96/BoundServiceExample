package com.harambe.refresherauto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cm_rv)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    ServiceInterface mService;

    boolean bound;

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder=(MyService.MyBinder)iBinder;
            mService=binder.getServiceInterface();
            mService.setMainPresenter(MainActivity.this);
            //Intent mInte=new Intent(MainActivity.this,MyService.class);
            //startService(mInte);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService.setUnBound();
        }
    };
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration  dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter=new MainAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!bound) {
            bound=true;
            Intent mInte = new Intent(this, MyService.class);
            bindService(mInte, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bound=false;
        mService.setUnBound();
        unbindService(mConnection);
    }

    @Override
    public void addDateItem(DateItem dateItem) {
        mAdapter.addDateItem(dateItem);
        layoutManager.scrollToPosition(0);
    }
}
