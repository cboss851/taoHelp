import com.tao.commons.utils.FileUtils;
import com.tao.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：cboss
 */
public class FreemarkerTemplateEngineTest {

    @Test
    public void test() throws Exception {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("tableName", "user");
        String templateName = "CustomMapper.java.ftl";
        String fullPathName = "D:\\bak\\tao\\userMapper.java";
        FreemarkerTemplateEngine.writer(objectMap, templateName, fullPathName);
    }
}