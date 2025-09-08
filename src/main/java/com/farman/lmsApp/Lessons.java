package com.farman.lmsApp;

public class Lessons {
    private int lessonId;
    private String title;
    private String content;
    private String videoUrl;

    public Lessons(int lessonId, String title, String content, String videoUrl) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }
}
