import org.example.launcher.App;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.*");

    public static void main(String[] args){
        ((App) context.getBean("app")).run();
        context.close();
    }
}
