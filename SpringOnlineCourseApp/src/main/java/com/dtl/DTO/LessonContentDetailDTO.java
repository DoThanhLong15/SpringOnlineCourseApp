/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.LessonContent;

/**
 *
 * @author LONG
 */
public class LessonContentDetailDTO extends LessonContentListDTO {

    private int duration;
    private String content;
    private int lessonId;
    private boolean hasLearn;

    public LessonContentDetailDTO() {

    }

    public LessonContentDetailDTO(LessonContent lessonContent) {
        super(lessonContent);
        this.duration = lessonContent.getDuration();
        this.content = lessonContent.getContent();
        this.lessonId = lessonContent.getLessonId().getId();
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the lessonId
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * @param lessonId the lessonId to set
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * @return the hasLearn
     */
    public boolean isHasLearn() {
        return hasLearn;
    }

    /**
     * @param hasLearn the hasLearn to set
     */
    public void setHasLearn(boolean hasLearn) {
        this.hasLearn = hasLearn;
    }
}
