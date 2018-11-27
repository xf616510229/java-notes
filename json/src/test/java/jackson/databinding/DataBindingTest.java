package jackson.databinding;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jackson.AbsBaseTester;
import jackson.bean.Country;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 使用数据绑定完成json序列化以及反序列化
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 14:21
 */
public class DataBindingTest extends AbsBaseTester {

    @Test
    public void serialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);

        Country country = this.getCountryJsonData();

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File("country.json"), country);
    }

    @Test
    public void deserialize() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);

        File json = this.getCountryFile();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Country country = mapper.readValue(json, Country.class);
        System.out.println("country:"+country);

    }

}
