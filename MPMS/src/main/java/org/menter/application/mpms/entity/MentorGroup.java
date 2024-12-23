package org.menter.application.mpms.entity;

public class MentorGroup {

    private Integer Id;
    private String GroupName;
    private Integer MentorID;
    private String MentorName;
    private Integer MenteeID;
    private String MenteeName;

    public MentorGroup() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public Integer getMentorID() {
        return MentorID;
    }

    public void setMentorID(Integer mentorID) {
        MentorID = mentorID;
    }

    public String getMentorName() {
        return MentorName;
    }

    public void setMentorName(String mentorName) {
        MentorName = mentorName;
    }

    public Integer getMenteeID() {
        return MenteeID;
    }

    public void setMenteeID(Integer menteeID) {
        MenteeID = menteeID;
    }

    public String getMenteeName() {
        return MenteeName;
    }

    public void setMenteeName(String menteeName) {
        MenteeName = menteeName;
    }
}
