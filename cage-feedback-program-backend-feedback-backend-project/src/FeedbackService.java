import java.util.List;

public interface FeedbackService {
    void addUser(User user);
    User authenticate(String username, String password);
    void addBatch(Batch batch);
    void assignBatchToStudent(String studentUsername, String batchName);
    void addQuestionToBatch(String batchName, String question);
    Batch findBatchByName(String batchName);
    void addFeedback(Feedback feedback);
    List<Feedback> getFeedbackByBatch(String batchName);
    List<Feedback> getFeedbackByStudent(String username);
    void editQuestion(String batchName, String oldQuestion, String newQuestion);
    void deleteQuestion(String batchName, String question);
    void deleteFeedback(String username, String batchName);
}