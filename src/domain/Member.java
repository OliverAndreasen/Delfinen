package domain;

public class Member {
    private int memberId;
    private String name;
    private int age;
    private boolean activeStatus;
    private String teamType;

    public Member(int memberId, String name, int age, boolean activeStatus, String teamType) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.activeStatus = activeStatus;
        this.teamType = teamType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void paySubscription() {}

    public void deleteAccount() {}

}
