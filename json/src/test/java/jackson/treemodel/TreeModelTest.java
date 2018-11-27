package jackson.treemodel;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson.AbsBaseTester;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Tree Model 完成Json的序列化和反序列化，这种方式类似fastjson中的JSONObject
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 14:48
 */
public class TreeModelTest extends AbsBaseTester {

    @Test
    public void serialize() throws IOException {
        // 创建json节点工厂
        JsonNodeFactory factory = new JsonNodeFactory(false);
        // 创建json工厂
        JsonFactory jsonFactory = new JsonFactory();
        //创建一个json生成器，用来指定json输出的方式
        JsonGenerator generator = jsonFactory.createGenerator(new FileWriter(this.getCountryFile()));

        // 默认情况下映射器不会指定根节点，设置根节点为country
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode country = factory.objectNode();
        // 添加两个普通属性
        country.put("country_id", "China");
        country.put("birthDate", "1949-10-01");

        // 添加json数组
        ArrayNode nation = factory.arrayNode().add("汉族").add("蒙古族").add("回族").add("藏族").add("满族");
        country.put("nation", nation);// 被弃用

        ArrayNode lakes = factory.arrayNode();
        lakes.add("QingHai Lake").add("Poyang Lake").add("Dongting Lake").add("Taihu Lake");
        country.set("lakes", lakes);

        ArrayNode provinces = factory.arrayNode();
        ObjectNode province = factory.objectNode();
        province.put("name", "上海");
        province.put("population", 37751200);
        ObjectNode province2 = factory.objectNode();
        province2.put("name", "浙江");
        province2.put("population", 55080000);
        provinces.add(province).add(province2);
        country.set("provinces", provinces);

        ObjectNode traffic = factory.objectNode();
        traffic.put("HighWay(KM)", 4240000);
        traffic.put("Train(KM)", 112000);
        country.set("traffic", traffic);

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeTree(generator, country);
    }

    @Test
    public void deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(getCountryFile());
        // 查看节点类型
        System.out.println("node JsonNodeType:" + node.getNodeType());
        // 是不是一个容器
        System.out.println("node is container Node ? " + node.isContainerNode());
        // 得到所有node节点的子节点名称
        System.out.println("---------得到所有node节点的子节点名称-------------------------");
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.print(fieldName + " ");
        }
        System.out.println("\n-----------------------------------------------------");
        // as.Text的作用是有值返回值，无值返回空字符串
        JsonNode country_id = node.get("country_id");
        System.out.println("country_id:" + country_id.asText() + " JsonNodeType:" + country_id.getNodeType());

        JsonNode birthDate = node.get("birthDate");
        System.out.println("birthDate:" + birthDate.asText() + " JsonNodeType:" + birthDate.getNodeType());

        JsonNode nation = node.get("nation");
        System.out.println("nation:" + nation + " JsonNodeType:" + nation.getNodeType());

        JsonNode lakes = node.get("lakes");
        System.out.println("lakes:" + lakes + " JsonNodeType:" + lakes.getNodeType());

        JsonNode provinces = node.get("provinces");
        System.out.println("provinces JsonNodeType:" + provinces.getNodeType());

        boolean flag = true;
        for (JsonNode provinceElements : provinces) {
            //为了避免provinceElements多次打印，用flag控制打印，能体现provinceElements的JsonNodeType就可以了
            if (flag) {
                System.out.println("provinceElements JsonNodeType:" + provinceElements.getNodeType());
                System.out.println("provinceElements is container node? " + provinceElements.isContainerNode());
                flag = false;
            }
            Iterator<String> provinceElementFields = provinceElements.fieldNames();
            while (provinceElementFields.hasNext()) {
                String fieldName = provinceElementFields.next();
                String province;
                if ("population".equals(fieldName)) {
                    province = fieldName + ":" + provinceElements.get(fieldName).asInt();
                } else {
                    province = fieldName + ":" + provinceElements.get(fieldName).asText();
                }
                System.out.println(province);
            }
        }
    }

}
