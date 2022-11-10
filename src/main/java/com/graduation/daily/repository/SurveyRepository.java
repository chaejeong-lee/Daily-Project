package com.graduation.daily.repository;

import com.graduation.daily.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findByType(Long type);
}
