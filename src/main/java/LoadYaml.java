
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.yaml.snakeyaml.Yaml;

/**
 * @program:
 * @description: TODO
 * @author: YJiang
 * @create: 2020-03-04 14:47
 */
public class LoadYaml {

    public static Map load(String datdFilePath) {
        Yaml yaml = new Yaml();
        List<Object> listYamlBlocks = new ArrayList<Object>();
        try {
            File file = new File(datdFilePath);
            Iterable<Object> objIterable
                    = yaml.loadAll(new FileInputStream(file));
            for (Object object : objIterable) {
                listYamlBlocks.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TypeConversion.stringToJavaBean(
                listYamlBlocks.get(
                        new Random().nextInt(listYamlBlocks.size())),
                Map.class);
    }

}