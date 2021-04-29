import org.example.entities.Questions;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void run(List<Questions> questions, User user) {
        List<String> answers = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        String input;

        while (true) {
            System.out.print("Enter name: ");
            input = scan.nextLine();
            if (input.equals("quit")) break;
            user.nullify();
            user.setName(input);

            int skipped = 0;
            for (Questions question : questions) {
                System.out.print(question.toString());
                input = scan.nextLine();
                if (input.isEmpty()) skipped++;
                answers.add(input);
            }
            user.writeReport(skipped, questions.size());
            System.out.println(user.toString());
        }
    }
}
