package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HIstoryAuthController {
    private HistoryRepository historyRepository;
    private HistoryAuthRepository historyAuthRepository;

    HIstoryAuthController(HistoryRepository historyRepository,
                          HistoryAuthRepository historyAuthRepository) {
        this.historyRepository = historyRepository;
        this.historyAuthRepository = historyAuthRepository;
    }

    @GetMapping("/history/{historyId}/auth")
    Page<HistoryAuth> getAllHistoryAuthByHistoryId(@PathVariable (value = "historyId") Long historyId,
                                                   Pageable pageable) {
        return historyAuthRepository.findByHistory(historyId, pageable);
    }

    @PostMapping("/history/{historyId}/auth")
    HistoryAuth createHIstoryAuth(@PathVariable (value = "historyId") Long historyId,
                                  @Valid @RequestBody HistoryAuth historyAuth) {
        History history = historyRepository.findById(historyId).get();
        historyAuth.setHistory(history);
        return historyAuthRepository.save(historyAuth);
    }
}
