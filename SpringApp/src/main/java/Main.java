import org.example.entities.Question;
import org.example.entities.data.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) {
        List<Question> questions = ((CSV) context.getBean("csv")).getQuestions("Example1.csv");
        System.out.println(questions);
        context.close();
    }
}
