package com.learnspring.userdetailsapi.interviews.repository;

import com.learnspring.userdetailsapi.interviews.model.InterviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewInfo, Long> {
}
