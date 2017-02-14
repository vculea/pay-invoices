package ro.jira;

public class Tasks {
    private String user;
    private String storyId;
    private String team;
    private String pi;
    private String activity;
    private String nameTask;
    private String hours;

    public Tasks(String user, String storyId, String team, String pi, String activity, String nameTask, String hours) {
        this.user = user;
        this.storyId = storyId;
        this.team = team;
        this.pi = pi;
        this.activity = activity;
        this.nameTask = nameTask;
        this.hours = hours;
    }

    public String getUser() {
        return user;
    }

    public String getStoryId() {
        return storyId;
    }

    public String getTeam() {
        return team;
    }

    public String getPi() {
        return pi;
    }

    public String getActivity() {
        return activity;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getHours() {
        return hours;
    }
}
