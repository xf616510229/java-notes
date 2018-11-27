package jackson.streaming;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import jackson.AbsBaseTester;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * StreamingTest
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 15:06
 */
public class StreamingTest extends AbsBaseTester {

    @Test
    public void serialize() throws IOException {
        JsonFactory factory = new JsonFactory();
        //从JsonFactory创建一个JsonGenerator生成器的实例
        JsonGenerator generator = factory.createGenerator(new FileWriter(getCountryFile()));

        generator.writeStartObject();
        generator.writeFieldName("country_id");
        generator.writeString("China");
        generator.writeFieldName("provinces");
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeStringField("name", "Shanxi");
        generator.writeNumberField("population", 33750000);
        generator.writeEndObject();
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
    }

    @Test
    public void deserialize() throws IOException {
        JsonFactory factory = new JsonFactory();
        // 从JsonFactory创建JsonParser解析器的实例
        JsonParser parser = factory.createParser(getCountryFile());

        while (!parser.isClosed()) {
            // 得到一个token,第一次遍历时，token指向json文件中第一个符号"{"
            JsonToken token = parser.nextToken();
            if (token == null) {
                break;
            }
            // 我们只查找 country3.json中的"population"字段的值，能体现解析的流程就可以了
            // 当key是provinces时，我们进入provinces,查找population
            if (JsonToken.FIELD_NAME.equals(token)
                    && "provinces".equals(parser.getCurrentName())) {
                token = parser.nextToken();
                if (!JsonToken.START_ARRAY.equals(token)) {
                    break;
                }
                // 此时，token指向的应该是"{"
                token = parser.nextToken();
                if (!JsonToken.START_OBJECT.equals(token)) {
                    break;
                }
                while (true) {
                    token = parser.nextToken();
                    if (token == null) {
                        break;
                    }
                    if (JsonToken.FIELD_NAME.equals(token)
                            && "population".equals(parser.getCurrentName())) {
                        token = parser.nextToken();
                        System.out.println(parser.getCurrentName() + " : "
                                + parser.getIntValue());
                    }
                }
            }
        }
    }

}
