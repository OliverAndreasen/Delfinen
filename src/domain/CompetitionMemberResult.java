package domain;

public class CompetitionMemberResult implements Comparable {
    Integer memberId;
    String resultTime;

    public CompetitionMemberResult(Integer memberId, String resultTime) {
        this.memberId = memberId;
        this.resultTime = resultTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getResultTime() {
        return resultTime;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof CompetitionMemberResult) {
            CompetitionMemberResult other = (CompetitionMemberResult) o;
            return resultTime.compareTo(other.resultTime);
        } else {
            return 0;
        }
    }
}