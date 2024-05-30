import java.util.List;

public class Feedback {
    private String user;
    private String batch;
    private List<String> responses;

    public Feedback(String user, String batch, List<String> responses) {
        this.user = user;
        this.batch = batch;
        this.responses = responses;
    }

    public String getUser() {
        return user;
    }

    public String getBatch() {
        return batch;
    }

    public List<String> getResponses() {
        return responses;
    }
}