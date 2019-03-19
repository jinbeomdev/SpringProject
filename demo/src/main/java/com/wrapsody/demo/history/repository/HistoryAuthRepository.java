package com.wrapsody.demo.history.repository;

import com.wrapsody.demo.history.HistoryAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryAuthRepository extends JpaRepository<HistoryAuth, Long> {
    List<HistoryAuth> findByHistoryId(Long historyId);
}
