import org.example.launcher.App;
import org.example.spring.configs.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args){
        ((App) context.getBean("app")).run();
        context.close();
    }
}
