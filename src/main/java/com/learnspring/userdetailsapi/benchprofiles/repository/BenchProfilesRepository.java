package com.learnspring.userdetailsapi.benchprofiles.repository;

import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenchProfilesRepository extends JpaRepository<BenchProfilesInfo, Long> {
}
