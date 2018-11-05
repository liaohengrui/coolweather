package coolweather.mapleo.xin.coolweather.choosearea;

import android.content.Context;

import java.util.IdentityHashMap;
import java.util.List;

import coolweather.mapleo.xin.coolweather.db.City;
import coolweather.mapleo.xin.coolweather.db.County;
import coolweather.mapleo.xin.coolweather.db.Province;
import coolweather.mapleo.xin.coolweather.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseareaPresenter extends BasePresenterImpl<ChooseareaContract.View> implements ChooseareaContract.Presenter {
    private ChooseareaModel model = new ChooseareaModel();

    @Override
    public void queryProvinces() {
        mView.showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Province> data = model.queryProvinces();
                mView.getActivitys().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showProvinces(data);
                        mView.closeProgressDialog();
                    }
                });
            }
        }).start();
    }

    @Override
    public void queryCities(int id) {
        mView.showProgressDialog();
        final int i = id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<City> data = model.queryCities(i);
                mView.getActivitys().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showCities(data);
                        mView.closeProgressDialog();
                    }
                });
            }
        }).start();
    }

    @Override
    public void queryCounties(final int id, final int id2) {
        mView.showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<County> data = model.queryCounties(id, id2);
                mView.getActivitys().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showCounties(data);
                        mView.closeProgressDialog();
                    }
                });
            }
        }).start();
    }
}
