import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用示例
 */
public class HelloSlf4j {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);
        logger.info("hello world");
        // 无配置文件输出样式 09:47:36.953 [main] INFO HelloSlf4j - hello world
        //                 时间          方法名 日志级别 日志名称(类名) 日志信息
        // Simple Log 风格
    }

}
