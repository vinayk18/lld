package SplitWise;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Expense {

    private final String id;
    private final User payer;
    private final int totalAmount;
    private final List<User> participants;
    private final SplitType splitType;
    private final Map<String, Integer> splitMetadata;
    private final long timestamp;
    private final String description;

    public Expense(
            String id,
            User payer,
            int totalAmount,
            List<User> participants,
            SplitType splitType,
            Map<String, Integer> splitMetadata,
            String description
    ) {
        if (payer == null) {
            throw new IllegalArgumentException("Payer cannot be null");
        }
        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Participants cannot be empty");
        }
        if (!participants.contains(payer)) {
            throw new IllegalArgumentException("Payer must be a participant");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        this.id = id;
        this.payer = payer;
        this.totalAmount = totalAmount;
        this.participants = List.copyOf(participants);
        this.splitType = splitType;
        this.splitMetadata = splitMetadata == null
                ? Collections.emptyMap()
                : Map.copyOf(splitMetadata);
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public User getPayer() {
        return payer;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public Map<String, Integer> getSplitMetadata() {
        return splitMetadata;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}