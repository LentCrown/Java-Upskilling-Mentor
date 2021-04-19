import org.example.data.entities.CSV;
import org.example.spring.configs.Config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import org.junit.jupiter.api.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Spring application testing")
public class SpringAppTest {
    private static String TEST_FILE;

    static {
        try {
            TEST_FILE = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                    .getResource("Example1.csv")).toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static AnnotationConfigApplicationContext context;
    private static CSV csv;

    @BeforeAll
    static void initContext() {
        context = new AnnotationConfigApplicationContext(Config.class);
        csv = (CSV) context.getBean("csv", TEST_FILE );
        System.out.println("File: " + TEST_FILE + "\n\n#######Starting test sequence######");
    }

    @BeforeEach
    void reOpenFile(){
        System.out.print("Re-opening file.. ");

        csv.reOpenFile();
        Assert.notNull(csv.getCsvReader(),"CSVReader=null");
        Assert.notNull(csv.getReader(), "Reader=null");

        System.out.println("OK.");
    }

    @Test
    @DisplayName("Read")
    void testRead(){
        System.out.print("Read file test.. ");

        String output = csv.read();
        Assert.hasText(output, "We failed read csv file content!");

        System.out.println("Passed.\n");
        System.out.println(output);
    }

    @Test
    @DisplayName("Close")
    void testReOpening(){
        System.out.print("Close file test.. ");

        String before_closing, after_closing;
        before_closing = csv.read();
        csv.reOpenFile();
        after_closing = csv.read();
        Assert.hasText(after_closing, "We failed to read csv file content!");
        Assertions.assertEquals(after_closing, before_closing);

        System.out.print("Passed.");
    }

    @AfterAll
    static void destroy(){
        csv = null;
        context = null;
    }

}
