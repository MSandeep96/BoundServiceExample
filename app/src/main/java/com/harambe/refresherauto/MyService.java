package com.harambe.refresherauto;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service implements ServiceInterface {

    private final IBinder mBinder=new MyBinder();

    private MainPresenter mPresenter;

    boolean bound;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void setMainPresenter(MainPresenter mMainPresenter) {
        mPresenter=mMainPresenter;
        bound=true;
        startFetching();
    }

    @Override
    public void gotResponse(DateItem body) {
        if(bound){
            mPresenter.addDateItem(body);
        }
    }

    @Override
    public void setUnBound() {
        bound=false;
    }

    private void startFetching() {
        final DataLayerInterface mDataLayer=new DataLayer();
        final Handler mHandler=new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDataLayer.fetchTime(MyService.this);
                if(bound){
                    mHandler.postDelayed(this,5000);
                }
            }
        });
    }

    public class MyBinder extends Binder {

        ServiceInterface getServiceInterface(){
            return MyService.this;
        }
    }
}
