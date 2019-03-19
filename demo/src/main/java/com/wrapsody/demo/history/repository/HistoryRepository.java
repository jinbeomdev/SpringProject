package com.wrapsody.demo.history.repository;

import com.wrapsody.demo.history.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByHistoryMasterIdAndHistoryIsDeleted(String historyMasterId, Boolean historyIsDeleted, Pageable pageable);
    List<History> findTop20ByHistoryMasterIdAndHistoryIsDeletedOrderByHistoryIsFavoriteDescAndCreatedAtDesc(String historyMasterId, Boolean historyIsDeleted);
}
