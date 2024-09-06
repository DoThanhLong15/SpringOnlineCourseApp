/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.ContentLearn;

/**
 *
 * @author LONG
 */
public interface ContentLearnRepository {

    void saveContentLearn(ContentLearn contentLearn);

    ContentLearn getContentLearn(int lessonContentId, int learnId);

    ContentLearn getContentLearnById(int contentLearnId);
}
