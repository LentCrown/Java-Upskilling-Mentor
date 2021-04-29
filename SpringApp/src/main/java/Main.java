import org.example.entities.Questions;
import org.example.entities.User;
import org.example.entities.data.CSV;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args){
        List<Questions> questions = ((CSV) context.getBean("csv")).getQuestions("Example1.csv");
        User user = ((User) context.getBean("user"));
        App.run(questions,user);
        context.close();
    }
}
