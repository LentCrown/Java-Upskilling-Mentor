import org.example.data.entities.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) {
        String path = null;
        try {
            path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                    .getResource("Example1.txt")).toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CSV.parseSettings(';', true);
        CSV file = (CSV) context.getBean("csv",path);
        String file_content = file.read();
        file.closeFile();
        System.out.println(file_content);
    }
}
