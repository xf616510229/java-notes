import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicUse {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer i;

    public void setTemperature(Integer temperature) {
        Integer oldI = i;
        i = temperature;
        logger.debug("Temperature set to {}. OldTemperature was {}", i, oldI);
        if (temperature > 50) {
            logger.info("Temperature has risen above 50 degrees");
        }
    }

    public static void main(String[] args) {
        ClassicUse useage = new ClassicUse();
        useage.setTemperature(50);
        useage.setTemperature(51);
    }
}
