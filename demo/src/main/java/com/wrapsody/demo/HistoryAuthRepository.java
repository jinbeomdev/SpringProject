package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryAuthRepository extends JpaRepository<HistoryAuth, Long> {
    Page<HistoryAuth> findByHistory(Long history, Pageable pageable);
}
