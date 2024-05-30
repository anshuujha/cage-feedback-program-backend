import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        BatchRepository batchRepository = new BatchRepositoryImpl();
        FeedbackService feedbackService = new FeedbackServiceImpl(userRepository, batchRepository);
        UserController userController = new UserControllerImpl(feedbackService);

        Scanner scanner = new Scanner(System.in);

        //few hardcoded users to check the functioning

        User admin1 = new User("Anshumann Jha", "anshu01", "admin01", "admin", "A1");
        User admin2 = new User("Anurag Pandey", "anurag02", "admin02", "admin", "A2");
        User student1 = new User("Rohan Singh", "rohan03", "student03", "student", "A1");
        User student2 = new User("Karan Kishore", "karan04", "student04", "student", "A1");
        User student3 = new User("Raman Singh", "raman05", "student05", "student", "A2");
        User student4 = new User("Varun Goyal", "varun06", "student06", "student", "A2");

        userController.addUser(admin1);
        userController.addUser(admin2);
        userController.addUser(student1);
        userController.addUser(student2);
        userController.addUser(student3);
        userController.addUser(student4);

        Batch batchA1 = new Batch("A1");
        Batch batchA2 = new Batch("A2");
        userController.addBatch(batchA1);
        userController.addBatch(batchA2);

        // demo questions for batches
        userController.addQuestionToBatch("A1", "Are you satisfied with the course content?");
        userController.addQuestionToBatch("A1", "How do you rate the instructor?");
        userController.addQuestionToBatch("A2", " Are the course materials sufficient ?");
        userController.addQuestionToBatch("A2", "How would you describe the interaction with fellow students ?");

        // Adding demo feedbacks
        List<String> responsesA1Student1 = Arrays.asList("Excellent", "Very Good");
        List<String> responsesA1Student2 = Arrays.asList("Good", "Good");
        List<String> responsesA2Student3 = Arrays.asList("Very Good", "Excellent");
        List<String> responsesA2Student4 = Arrays.asList("Good", "Average");

        Feedback feedback1 = new Feedback(student1.getUsername(), "A1", responsesA1Student1);
        Feedback feedback2 = new Feedback(student2.getUsername(), "A1", responsesA1Student2);
        Feedback feedback3 = new Feedback(student3.getUsername(), "A2", responsesA2Student3);
        Feedback feedback4 = new Feedback(student4.getUsername(), "A2", responsesA2Student4);

        userController.addFeedback(feedback1);
        userController.addFeedback(feedback2);
        userController.addFeedback(feedback3);
        userController.addFeedback(feedback4);

        while (true) {
            System.out.println("Welcome to the Feedback App");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.println("Enter The Number Corresponding With The Function To Proceed ! ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                signUp(userController, scanner);
            } else if (choice == 2) {
                signIn(userController, scanner);
            } else if (choice == 3) {
                System.out.println("Exiting the application.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void signUp(UserController userController, Scanner scanner) {
        System.out.println("Sign Up");

        System.out.println("Enter your full name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        String password;
        while (true) {
            System.out.println("Enter your password (must contain both letters and numbers):");
            password = scanner.nextLine();
            if (isValidPassword(password)) {
                break;
            } else {
                System.out.println("Invalid password. Try again.");
            }
        }

        System.out.println("Enter your batch (A1 or A2):");
        String batch = scanner.nextLine();

        System.out.println("Enter your role (student or admin):");
        String role = scanner.nextLine();

        User newUser = new User(fullName, username, password, role, batch);
        userController.addUser(newUser);
        System.out.println("Welcome onboard, you've successfully registered on the feedback app!");
    }

    private static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static void signIn(UserController userController, Scanner scanner) {
        System.out.println("Sign In");

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user = userController.authenticate(username, password);
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                adminMenu(user, userController, scanner);
            } else if (user.getRole().equalsIgnoreCase("student")) {
                studentMenu(user, userController, scanner);
            }
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    private static void adminMenu(User admin, UserController userController, Scanner scanner) {
        System.out.println("Welcome " + admin.getFullName() + " (Administrator)");

        while (true) {
            System.out.println("1. Create a new batch");
            System.out.println("2. Assign a batch to student");
            System.out.println("3. Create a question for batch A1");
            System.out.println("4. Create a question for batch A2");
            System.out.println("5. Edit a question");
            System.out.println("6. Delete a question");
            System.out.println("7. Get feedbacks by batch");
            System.out.println("8. Get feedback by student name");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter batch name:");
                    String batchName = scanner.nextLine();
                    Batch newBatch = new Batch(batchName);
                    userController.addBatch(newBatch);
                    System.out.println("Batch created successfully.");
                    break;
                case 2:
                    System.out.println("Enter student username:");
                    String studentUsername = scanner.nextLine();
                    System.out.println("Enter batch name:");
                    batchName = scanner.nextLine();
                    userController.assignBatchToStudent(studentUsername, batchName);
                    System.out.println("Batch assigned successfully.");
                    break;
                case 3:
                    System.out.println("Enter question for batch A1:");
                    String questionA1 = scanner.nextLine();
                    userController.addQuestionToBatch("A1", questionA1);
                    System.out.println("Question created successfully.");
                    break;
                case 4:
                    System.out.println("Enter question for batch A2:");
                    String questionA2 = scanner.nextLine();
                    userController.addQuestionToBatch("A2", questionA2);
                    System.out.println("Question created successfully.");
                    break;
                case 5:
                    System.out.println("Enter batch name:");
                    batchName = scanner.nextLine();
                    System.out.println("Enter the question to edit:");
                    String oldQuestion = scanner.nextLine();
                    System.out.println("Enter the new question:");
                    String newQuestion = scanner.nextLine();
                    userController.editQuestion(batchName, oldQuestion, newQuestion);
                    System.out.println("Question edited successfully.");
                    break;
                case 6:
                    System.out.println("Enter batch name:");
                    batchName = scanner.nextLine();
                    System.out.println("Enter the question to delete:");
                    String questionToDelete = scanner.nextLine();
                    userController.deleteQuestion(batchName, questionToDelete);
                    System.out.println("Question deleted successfully.");
                    break;
                case 7:
                    System.out.println("Enter batch name:");
                    batchName = scanner.nextLine();
                    List<Feedback> feedbacksByBatch = userController.getFeedbackByBatch(batchName);
                    for (Feedback feedback : feedbacksByBatch) {
                        List<String> questions = userController.getQuestionsByBatch(feedback.getBatch());
                        List<String> responses = feedback.getResponses();
                        System.out.println("User: " + feedback.getUser());
                        for (int i = 0; i < questions.size(); i++) {
                            System.out.println("Question: " + questions.get(i));
                            System.out.println("Response: " + responses.get(i));
                        }
                    }
                    break;
                case 8:
                    System.out.println("Enter student username:");
                    String studentUsernameToFind = scanner.nextLine();
                    List<Feedback> feedbacksByStudent = userController.getFeedbackByStudent(studentUsernameToFind);
                    for (Feedback feedback : feedbacksByStudent) {
                        List<String> questions = userController.getQuestionsByBatch(feedback.getBatch());
                        List<String> responses = feedback.getResponses();
                        System.out.println("User: " + feedback.getUser());
                        for (int i = 0; i < questions.size(); i++) {
                            System.out.println("Question: " + questions.get(i));
                            System.out.println("Response: " + responses.get(i));
                        }
                    }
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void studentMenu(User student, UserController userController, Scanner scanner) {
        System.out.println("Welcome " + student.getFullName() + " to the Feedback App");

        while (true) {
            System.out.println("1. Provide feedback");
            System.out.println("2. Review your feedback");
            System.out.println("3. See your batch feedbacks");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Batch studentBatch = userController.findBatchByName(student.getBatch());
                    if (studentBatch != null) {
                        List<String> responses = new ArrayList<>();
                        for (String question : studentBatch.getQuestions()) {
                            System.out.println(question);
                            String response = scanner.nextLine();
                            responses.add(response);
                        }
                        Feedback feedback = new Feedback(student.getUsername(), student.getBatch(), responses);
                        userController.addFeedback(feedback);
                        System.out.println("Feedback submitted.");
                    } else {
                        System.out.println("Batch not found.");
                    }
                    break;
                case 2:
                    List<Feedback> feedbacks = userController.getFeedbackByStudent(student.getUsername());
                    for (Feedback feedback : feedbacks) {
                        Batch feedbackBatch = userController.findBatchByName(feedback.getBatch());
                        List<String> questions = feedbackBatch.getQuestions();
                        List<String> responses = feedback.getResponses();
                        for (int i = 0; i < questions.size(); i++) {
                            System.out.println("Question: " + questions.get(i));
                            System.out.println("Your response: " + responses.get(i));
                        }
                    }
                    break;
                case 3:
                    studentBatch = userController.findBatchByName(student.getBatch());
                    if (studentBatch != null) {
                        feedbacks = userController.getFeedbackByBatch(student.getBatch());
                        for (Feedback feedback : feedbacks) {
                            System.out.println("User: " + feedback.getUser());
                            List<String> questions = studentBatch.getQuestions();
                            List<String> responses = feedback.getResponses();
                            for (int i = 0; i < questions.size(); i++) {
                                System.out.println("Question: " + questions.get(i));
                                System.out.println("Response: " + responses.get(i));
                            }
                        }
                    } else {
                        System.out.println("Batch not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting student menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}