/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.DoingExercise;
import com.dtl.repository.DoingExerciseRepository;
import com.dtl.services.DoingExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class DoingExerciseServiceImpl implements DoingExerciseService{
    
    @Autowired
    private DoingExerciseRepository doingExerciseRepo;

    @Override
    public void saveDoingExercise(DoingExercise exercise) {
        this.doingExerciseRepo.saveDoingExercise(exercise);
    }

    @Override
    public DoingExercise getDoingExerciseById(int id) {
        return this.doingExerciseRepo.getDoingExerciseById(id);
    }

    @Override
    public DoingExercise getDoingExercise(int learnerId, int contentId) {
        return this.doingExerciseRepo.getDoingExercise(learnerId, contentId);
    }
    
}
