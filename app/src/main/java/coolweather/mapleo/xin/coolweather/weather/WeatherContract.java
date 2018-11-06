package coolweather.mapleo.xin.coolweather.weather;

import android.content.Context;

import coolweather.mapleo.xin.coolweather.mvp.BasePresenter;
import coolweather.mapleo.xin.coolweather.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WeatherContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
