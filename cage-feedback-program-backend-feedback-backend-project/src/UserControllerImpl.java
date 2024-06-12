import java.util.List;

public class UserControllerImpl implements UserController {
    private FeedbackService feedbackService;

    public UserControllerImpl(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public void addUser(User user) {
        feedbackService.addUser(user);
    }

    @Override
    public User authenticate(String username, String password) {
        return feedbackService.authenticate(username, password);
    }

    @Override
    public void addBatch(Batch batch) {
        feedbackService.addBatch(batch);
    }

    @Override
    public void assignBatchToStudent(String studentUsername, String batchName) {
        feedbackService.assignBatchToStudent(studentUsername, batchName);
    }

    @Override
    public void addQuestionToBatch(String batchName, String question) {
        feedbackService.addQuestionToBatch(batchName, question);
    }

    @Override
    public Batch findBatchByName(String batchName) {
        return feedbackService.findBatchByName(batchName);
    }

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackService.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getFeedbackByBatch(String batchName) {
        return feedbackService.getFeedbackByBatch(batchName);
    }

    @Override
    public List<Feedback> getFeedbackByStudent(String username) {
        return feedbackService.getFeedbackByStudent(username);
    }
    @Override
    public void editQuestion(String batchName, String oldQuestion, String newQuestion) {
        feedbackService.editQuestion(batchName, oldQuestion, newQuestion);
    }

    @Override
    public void deleteQuestion(String batchName, String question) {
        feedbackService.deleteQuestion(batchName, question);
    }
    @Override
    public List<String> getQuestionsByBatch(String batchName) {
        Batch batch = feedbackService.findBatchByName(batchName);
        return batch != null ? batch.getQuestions() : null;
    }
    @Override
    public void deleteFeedback(String username, String batchName) {
        feedbackService.deleteFeedback(username, batchName);
    }

}