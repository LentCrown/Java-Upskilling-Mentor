import org.example.entities.Question;
import org.example.entities.data.CSV;
import org.example.spring.configs.Config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Spring application testing")
public class SpringAppTest {
    private static String TEST_FILE = "Example1.csv";
    private static boolean IS_RELATIVE = true;

    private static AnnotationConfigApplicationContext context;
    private static CSV csv;

    @BeforeAll
    static void initContext() {
        context = new AnnotationConfigApplicationContext(Config.class);
        csv = context.getBean("csv", CSV.class);
        System.out.println("File: " + TEST_FILE + "\n\n#######Starting test sequence######");
    }

    @Test
    @DisplayName("Read")
    void testRead(){
        System.out.print("Read file test.. ");

        String output = csv.read(TEST_FILE);
        Assert.hasText(output, "We failed to read csv file content!");

        System.out.println("Passed.\n");
        System.out.println(output);
    }

    @Test
    @DisplayName("Close")
    void testReOpening(){
        System.out.print("Close file test.. ");

        String before_closing, after_closing;
        before_closing = csv.read(TEST_FILE);
        after_closing = csv.read(TEST_FILE);
        Assert.hasText(after_closing, "We failed to read csv file content!");
        Assertions.assertEquals(after_closing, before_closing);

        System.out.print("Passed.");
    }

    @Test
    @DisplayName("Get questions")
    void testQuestions(){
        System.out.print("Get list of <Question> objects.. ");

        List<Question> questionList = csv.getQuestions(TEST_FILE);
        Assert.notNull(questionList, "Error in getting list of objects");

        System.out.print("Passed.\n");
        System.out.println(questionList);
    }

    @AfterAll
    static void destroy(){
        csv = null;
        context.close();
        context = null;
    }

}