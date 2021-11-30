package domain;

public class TrainingResult implements Comparable {
    Integer memberId;
    String trainingTime;

    public TrainingResult(Integer memberId, String trainingTime) {
        this.memberId = memberId;
        this.trainingTime = trainingTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof TrainingResult) {
            TrainingResult other = (TrainingResult) o;
            return trainingTime.compareTo(other.trainingTime);
        }
        else {
            return 0;
        }
    }
}