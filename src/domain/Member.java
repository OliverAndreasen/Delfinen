package domain;

public class Member {
    private String firstName;
    private String lastName;
    private int age;
    private boolean activeStatus;
    private String divisionType;

    public Member(String firstName,String lastName, int age, boolean activeStatus, String divisionType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.activeStatus = activeStatus;
        this.divisionType = divisionType;
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

    public String getDivisionType() {
        return divisionType;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }


}
