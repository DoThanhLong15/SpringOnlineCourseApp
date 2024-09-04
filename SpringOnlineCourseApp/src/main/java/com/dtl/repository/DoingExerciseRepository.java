/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.DoingExercise;

/**
 *
 * @author LONG
 */
public interface DoingExerciseRepository {

    void saveDoingExercise(DoingExercise exercise);

    DoingExercise getDoingExerciseById(int id);

    DoingExercise getDoingExercise(int learnerId, int contentId);
}
