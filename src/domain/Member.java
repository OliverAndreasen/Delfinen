package domain;

public class Member {
    private int memberId;
    private String name;
    private int age;
    private boolean activeStatus;
    private String teamType;
    private boolean paidThisYear;

    public Member(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.activeStatus = activeStatus;
        this.teamType = teamType;
        this.paidThisYear = paidThisYear;
    }

    public boolean getPaidThisYear() {
        return paidThisYear;
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

    public boolean getActiveStatus() {
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

    public String toString() {
        String result = "";
        result += "Medlemsid: " + getMemberId() + "\n";
        result += "Navn: " + getName() + "\n";
        result += "Alder: " + getAge() + "\n";
        result += "Aktiv status: " + getActiveStatus() + "\n";
        result += "Hold type: " + getTeamType() + "\n\n";
        return result;
    }


}
