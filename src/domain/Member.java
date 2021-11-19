package domain;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private int age;
    private boolean activeStatus;
    private String teamType;

    public Member(int memberId, String firstName, String lastName, int age, boolean activeStatus, String teamType) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.activeStatus = activeStatus;
        this.teamType = teamType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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


    public void paySubscription() {

    }

    public void deleteAccount() {

    }
}
