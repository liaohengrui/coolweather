package coolweather.mapleo.xin.coolweather.choosearea;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import coolweather.mapleo.xin.coolweather.db.City;
import coolweather.mapleo.xin.coolweather.db.County;
import coolweather.mapleo.xin.coolweather.db.Province;
import coolweather.mapleo.xin.coolweather.mvp.BasePresenter;
import coolweather.mapleo.xin.coolweather.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseareaContract {
    interface View extends BaseView {
        void showProvinces(List<Province> data);

        void showCities(List<City> data);

        void showCounties(List<County> data);

        void showProgressDialog();

        void closeProgressDialog();

        FragmentActivity getActivitys();

    }

    interface Presenter extends BasePresenter<View> {

        void queryProvinces();

        void queryCities(int id);

        void queryCounties(int id, int id2);

    }
}
