package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryTagRepository extends JpaRepository<HistoryTag, Long> {
    Page<HistoryTag> findByHistory(Long history, Pageable pageable);

}
