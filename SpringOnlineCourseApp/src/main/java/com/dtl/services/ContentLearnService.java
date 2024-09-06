/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.ContentLearn;

/**
 *
 * @author LONG
 */
public interface ContentLearnService {

    void saveContentLearn(ContentLearn contentLearn);

    ContentLearn getContentLearn(int lessonContentId, int learnerId);

    ContentLearn getContentLearnById(int contentLearnId);
}
