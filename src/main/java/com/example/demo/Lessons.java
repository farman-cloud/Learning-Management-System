package com.example.demo;

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

    @Override
    public String toString() {
        return "Lesson{" + "id=" + lessonId + ", title='" + title + "'}";
    }
}
