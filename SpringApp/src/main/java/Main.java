import org.example.data.entities.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) {
        CSV file = (CSV) context.getBean("csv","Example1.csv");
        String file_content = file.read();
        file.close();
        System.out.println(file_content);
    }
}
