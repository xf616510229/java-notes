package jackson;

import jackson.bean.Country;
import jackson.bean.Province;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * jackson.AbsBaseTester
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 14:45
 */
public class AbsBaseTester {

    protected Country getCountryJsonData() {
        Country country = new Country();
        country.setCountry_id("China");
        country.setBirthDate(new Date(1949, 10, 1));
        country.setLakes(new String[] { "扬州瘦西湖", "杭州西湖", "洞庭湖", "太湖" });

        List<String> nation = new ArrayList<>();
        nation.add("汉族");
        nation.add("满族");
        nation.add("蒙古族");
        nation.add("回族");
        nation.add("藏族");
        country.setNation(nation);

        Province province = new Province();
        province.setName("上海");
        province.setPopulation(37751200);
        Province province2 = new Province();
        province2.setName("浙江");
        province2.setPopulation(55080000);
        List<Province> provinces = new ArrayList<Province>();
        provinces.add(province);
        provinces.add(province2);
        country.setProvinces(provinces);

        country.addTraffic("Train(KM)", 112000);
        country.addTraffic("HighWay(KM)", 4240000);
        return country;
    }

    protected File getCountryFile() {
        return new File("country.json");
    }

}
