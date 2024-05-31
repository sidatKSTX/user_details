package com.learnspring.userdetailsapi.dailysubmissions.repository;

import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySubmissionsRepository extends JpaRepository<DailySubmissionsInfo, Long> {
}
