package coolweather.mapleo.xin.coolweather.choosearea;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import coolweather.mapleo.xin.coolweather.db.City;
import coolweather.mapleo.xin.coolweather.db.County;
import coolweather.mapleo.xin.coolweather.db.Province;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChooseareaModel {

    public List<Province> queryProvinces() {
        List<Province> provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            return provinceList;
        } else {
            String address = "http://guolin.tech/api/china";
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(address)
                    .get()//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();

                return JSONArray.parseArray(response.body().string(), Province.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<City> queryCities(int id) {
        List<City> cityList = DataSupport.where("provinceid = ?", String.valueOf(id)).find(City.class);
        if (cityList.size() > 0) {
            return cityList;
        } else {
            String address = "http://guolin.tech/api/china/" + id;
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(address)
                    .get()//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();

                return JSONArray.parseArray(response.body().string(), City.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<County> queryCounties(int id, int id2) {
        List<County> countyList = DataSupport.where("cityid = ?", String.valueOf(id2)).find(County.class);
        if (countyList.size() > 0) {
            return countyList;
        } else {
            String address = "http://guolin.tech/api/china/" + id + "/" + id2;
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(address)
                    .get()//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                return JSONArray.parseArray(response.body().string(), County.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
