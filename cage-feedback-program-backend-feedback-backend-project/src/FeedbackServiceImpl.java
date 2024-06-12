import java.util.ArrayList;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private UserRepository userRepository;
    private BatchRepository batchRepository;
    private List<Feedback> feedbacks = new ArrayList<>();

    public FeedbackServiceImpl(UserRepository userRepository, BatchRepository batchRepository) {
        this.userRepository = userRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public User authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }

    @Override
    public void addBatch(Batch batch) {
        batchRepository.addBatch(batch);
    }

    @Override
    public void assignBatchToStudent(String studentUsername, String batchName) {
        User user = userRepository.findUserByUsername(studentUsername);
        Batch batch = batchRepository.findBatchByName(batchName);
        if (user != null && batch != null) {
            user.setBatch(batchName);
            batch.addStudent(user);
        }
    }

    @Override
    public void addQuestionToBatch(String batchName, String question) {
        Batch batch = batchRepository.findBatchByName(batchName);
        if (batch != null) {
            batch.addQuestion(question);
        }
    }

    @Override
    public Batch findBatchByName(String batchName) {
        return batchRepository.findBatchByName(batchName);
    }

    @Override
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    @Override
    public List<Feedback> getFeedbackByBatch(String batchName) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            if (feedback.getBatch().equals(batchName)) {
                result.add(feedback);
            }
        }
        return result;
    }

    @Override
    public List<Feedback> getFeedbackByStudent(String username) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            if (feedback.getUser().equals(username)) {
                result.add(feedback);
            }
        }
        return result;
    }
    @Override
    public void editQuestion(String batchName, String oldQuestion, String newQuestion) {
        Batch batch = batchRepository.findBatchByName(batchName);
        if (batch != null) {
            List<String> questions = batch.getQuestions();
            int index = questions.indexOf(oldQuestion);
            if (index != -1) {
                questions.set(index, newQuestion);
                System.out.println("Question updated successfully.");
            } else {
                System.out.println("Question not found.");
            }
        } else {
            System.out.println("Batch not found.");
        }
    }

    @Override
    public void deleteQuestion(String batchName, String question) {
        Batch batch = batchRepository.findBatchByName(batchName);
        if (batch != null) {
            List<String> questions = batch.getQuestions();
            if (questions.contains(question)) {
                questions.set(questions.indexOf(question),null);
                System.out.println("Question deleted successfully.");
            } else {
                System.out.println("Question not found.");
            }
        } else {
            System.out.println("Batch not found.");
        }
    }
    @Override
    public void deleteFeedback(String username, String batchName) {
        boolean found = false;
        for (Feedback feedback : feedbacks) {
            if (feedback.getUser().equals(username) && feedback.getBatch().equals(batchName)) {
                feedback.setActive(false);
                System.out.println("Feedback deleted successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Feedback not found.");
        }
    }

}