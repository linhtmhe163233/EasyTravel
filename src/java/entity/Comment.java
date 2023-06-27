
package entity;

import java.sql.Timestamp;

public class Comment {
    private Timestamp time;
    private String content;
    private int userID;
    private int threadID;

    public Comment() {
    }

    public Comment(Timestamp time, String content, int userID, int threadID) {
        this.time = time;
        this.content = content;
        this.userID = userID;
        this.threadID = threadID;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getUserID() {
        return userID;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    @Override
    public String toString() {
        return "Comment{" + "time=" + time + ", content=" + content + ", userID=" + userID + ", threadID=" + threadID + '}';
    }
    
    

}
