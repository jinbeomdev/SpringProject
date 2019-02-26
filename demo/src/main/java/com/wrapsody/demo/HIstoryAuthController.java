package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    List<HistoryAuth> getAllHistoryAuthByHistoryId(@PathVariable (value = "historyId") Long historyId) {
        return historyAuthRepository.findByHistoryHistoryId(historyId);
    }

    @PostMapping("/history/{historyId}/auth")
    HistoryAuth createHIstoryAuth(@PathVariable (value = "historyId") Long historyId,
                                  @Valid @RequestBody RequestCreateHistoryAuthDto requestCreateHistoryAuthDto) {
        History history = historyRepository.findById(historyId).get();
        return requestCreateHistoryAuthDto.toEntity(history);
    }
}
