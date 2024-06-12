public interface BatchRepository {
    void addBatch(Batch batch);
    Batch findBatchByName(String batchName);
}