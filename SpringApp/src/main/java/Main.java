import org.example.entities.data.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) {
        List<String[]> dataRaw = context.getBean("csv", CSV.class).readRawLines();
        List<String> dataColumn = context.getBean("csv", CSV.class).readColumn("Question");
        String data = context.getBean("csv", CSV.class).read();
        System.out.println(dataRaw);
        System.out.println();
        System.out.println(dataColumn);
        System.out.println();
        System.out.println(data);
    }
}
