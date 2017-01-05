package com.harambe.refresherauto;

/**
 * Created by Sandeep on 05-01-2017.
 */

public interface ServiceInterface {

    void setMainPresenter(MainPresenter mMainPresenter);

    void gotResponse(DateItem body);

    void setUnBound();
}
