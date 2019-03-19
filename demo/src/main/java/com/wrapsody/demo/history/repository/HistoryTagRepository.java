package com.wrapsody.demo.history.repository;

import com.wrapsody.demo.history.HistoryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryTagRepository extends JpaRepository<HistoryTag, Long> {
    List<HistoryTag> findByHistoryId(Long historyId);
}
