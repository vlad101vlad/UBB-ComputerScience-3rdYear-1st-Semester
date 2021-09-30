package en.ubbcluj.info;

public class Operation {
    public static int operationIDCounter = 1;

    private int operationID;
    private int sourceID;
    private int destinationID;
    private int amount;

    public Operation(int sourceID, int destinationID, int amount) {
        this.operationID = operationIDCounter;
        operationIDCounter++;

        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.amount = amount;
    }

    public int getOperationID() {
        return operationID;
    }

    public int getSourceID() {
        return sourceID;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Operation{" +
            "operationID=" + operationID +
            ", sourceID=" + sourceID +
            ", destinationID=" + destinationID +
            ", amount=" + amount +
            '}';
    }
}
