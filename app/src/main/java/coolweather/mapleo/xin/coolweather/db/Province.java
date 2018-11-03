package coolweather.mapleo.xin.coolweather.db;

import org.litepal.crud.DataSupport;

import lombok.Data;

@Data
public class Province extends DataSupport {
    private int id;
    private String provinceName;
    private int provinceCode;
}
