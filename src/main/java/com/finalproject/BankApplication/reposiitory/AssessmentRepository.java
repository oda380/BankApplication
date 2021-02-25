package com.finalproject.BankApplication.reposiitory;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.AssessmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer> {
    public Assessment findFirstByOrderByIdDesc();
    public Assessment findAssessmentById(int id);
    public List<Assessment> findAssessmentByType(AssessmentType type);
    public List<Assessment> findAssessmentByStatus(AssessmentStatus status);

}
