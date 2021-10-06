package en.ubbcluj.info.domain;

/**
 * Class used for representing a transfer(operation) from one account to another
 */
public class Operation {
    public static int operationIDCounter = 1;

    private final int operationID;
    private final int sourceID;
    private final int destinationID;
    private final int amount;

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
        return String.format("\noperation#%d ~ %d money from %d to %d", operationID, amount, sourceID, destinationID);
    }
}
