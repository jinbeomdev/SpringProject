package com.wrapsody.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryTagRepository extends JpaRepository<HistoryTag, Long> {
    List<HistoryTag> findByHistoryId(Long historyId);
}
