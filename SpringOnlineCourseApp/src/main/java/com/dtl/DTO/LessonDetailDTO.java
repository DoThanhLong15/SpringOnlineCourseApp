/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.Lesson;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author LONG
 */
public class LessonDetailDTO extends LessonListDTO{
    private List<LessonContentListDTO> lessonContentList;
    private Integer courseId;
    
    public LessonDetailDTO(){
        
    }
    
    public LessonDetailDTO(Lesson lesson){
        super(lesson);
        this.courseId = lesson.getCourseId().getId();
        this.lessonContentList = lesson.getLessonContentCollection()
                .stream()
                .map(lessonContent -> new LessonContentListDTO(lessonContent))
                .collect(Collectors.toList());
    }

    /**
     * @return the lessonContentList
     */
    public List<LessonContentListDTO> getLessonContentList() {
        return lessonContentList;
    }

    /**
     * @param lessonContentList the lessonContentList to set
     */
    public void setLessonContentList(List<LessonContentListDTO> lessonContentList) {
        this.lessonContentList = lessonContentList;
    }

    /**
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
