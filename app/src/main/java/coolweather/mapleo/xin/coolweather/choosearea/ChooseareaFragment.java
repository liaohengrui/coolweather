package coolweather.mapleo.xin.coolweather.choosearea;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import coolweather.mapleo.xin.coolweather.R;
import coolweather.mapleo.xin.coolweather.choosearea.adapter.PlaceAdapter;
import coolweather.mapleo.xin.coolweather.choosearea.adapter.RecycleViewDivider;
import coolweather.mapleo.xin.coolweather.db.City;
import coolweather.mapleo.xin.coolweather.db.County;
import coolweather.mapleo.xin.coolweather.db.Province;
import coolweather.mapleo.xin.coolweather.mvp.MVPBaseFragment;
import coolweather.mapleo.xin.coolweather.weather.WeatherActivity;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author Henry
 */

public class ChooseareaFragment extends MVPBaseFragment<ChooseareaContract.View, ChooseareaPresenter> implements ChooseareaContract.View, PlaceAdapter.OnItemClickListener {
    private static final String TAG = "ChooseAreaFragment";

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;
    @BindView(R.id.title_text)
    TextView titleText;

    @BindView(R.id.back_button)
    Button backButton;

    @BindView(R.id.list_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;

    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * 当前选中的级别
     */
    private int currentLevel;

    private List<String> dataList = new ArrayList<>();
    PlaceAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceAdapter(dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));
        adapter.setItemClickListener(this);
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        mPresenter.queryProvinces();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void showProvinces(List<Province> data) {
        provinceList = data;
        dataList.clear();
        for (Province province : provinceList) {
            dataList.add(province.getName());
        }
        adapter.notifyDataSetChanged();
        currentLevel = LEVEL_PROVINCE;
    }

    @Override
    public void showCities(List<City> data) {
        titleText.setText(selectedProvince.getName());
        backButton.setVisibility(View.VISIBLE);
        cityList = data;
        dataList.clear();
        for (City city : cityList) {
            dataList.add(city.getName());
        }
        adapter.notifyDataSetChanged();
        currentLevel = LEVEL_CITY;
    }

    @Override
    public void showCounties(List<County> data) {
        titleText.setText(selectedProvince.getName());
        backButton.setVisibility(View.VISIBLE);
        countyList = data;
        dataList.clear();
        for (County county : countyList) {
            dataList.add(county.getName());
        }
        adapter.notifyDataSetChanged();
        currentLevel = LEVEL_COUNTY;
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onItemClick(int position) {
        if (currentLevel == LEVEL_PROVINCE) {
            selectedProvince = provinceList.get(position);
            mPresenter.queryCities(selectedProvince.getId());
        } else if (currentLevel == LEVEL_CITY) {
            selectedCity = cityList.get(position);
            mPresenter.queryCounties(selectedProvince.getId(), selectedCity.getId());
        } else if (currentLevel == LEVEL_COUNTY) {
            String weatherId = countyList.get(position).getWeatherId();
            Intent intent = new Intent(getActivitys(), WeatherActivity.class);
            intent.putExtra("weather_id", weatherId);
            startActivity(intent);
            getActivitys().finish();
        }
    }

    @Override
    public FragmentActivity getActivitys() {
        return super.getActivity();

    }
}
