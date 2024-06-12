import java.util.ArrayList;
import java.util.List;

public class BatchRepositoryImpl implements BatchRepository {
    private List<Batch> batches = new ArrayList<>();

    @Override
    public void addBatch(Batch batch) {
        batches.add(batch);
    }

    @Override
    public Batch findBatchByName(String name) {
        for (Batch batch : batches) {
            if (batch.getName().equals(name)) {
                return batch;
            }
        }
        return null;
    }
}