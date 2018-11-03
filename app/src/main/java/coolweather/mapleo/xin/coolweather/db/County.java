package coolweather.mapleo.xin.coolweather.db;

import android.service.autofill.Dataset;

import org.litepal.crud.DataSupport;

import lombok.Data;

@Data
public class County extends DataSupport {
    private int id;
    private String contyName;
    private String weatherId;
    private int cityName;
}
