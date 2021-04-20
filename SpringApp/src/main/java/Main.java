import org.example.data.entities.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) {
        //String file_content = ((CSV) context.getBean("param", "Example1.csv")).read();
        String file_content = ((CSV) context.getBean("basic")).read();
        System.out.println(file_content);
    }
}
