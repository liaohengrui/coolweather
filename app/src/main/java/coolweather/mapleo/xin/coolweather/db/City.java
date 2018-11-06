package coolweather.mapleo.xin.coolweather.db;

import org.litepal.crud.DataSupport;

import lombok.Data;

@Data
public class City extends DataSupport {

    private int id;
    private String name;
    private int cityCode;
    private int provinceId;


}

