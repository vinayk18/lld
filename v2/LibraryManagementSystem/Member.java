package LibraryManagementSystem;

public class Member {
    private final int memberId;
    private final String name;
    private final int maxAllowedBorrows;

    public Member(int memberId, String name, int maxAllowedBorrows) {
        this.memberId = memberId;
        this.name = name;
        this.maxAllowedBorrows = maxAllowedBorrows;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getMaxAllowedBorrows() {
        return maxAllowedBorrows;
    }
}