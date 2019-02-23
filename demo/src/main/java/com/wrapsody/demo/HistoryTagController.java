package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HistoryTagController {
    private  HistoryRepository historyRepository;
    private  HistoryTagRepository historyTagRepository;

    HistoryTagController(HistoryRepository historyRepository, HistoryTagRepository historyTagRepository) {
        this.historyRepository = historyRepository;
        this.historyTagRepository = historyTagRepository;
    }

    @GetMapping("/history/{historyId}/tag")
    public Page<HistoryTag> getAllHistoryTagByHistroyId(@PathVariable (value =  "historyId") Long historyId,
                                                        Pageable pageable) {
        return historyTagRepository.findByHistory(historyId, pageable);
    }

    @PostMapping("/history/{historyId}/tag")
    public HistoryTag createHistoryTag(@PathVariable (value = "historyId") Long historyId,
                                       @Valid @RequestBody HistoryTag historyTag) {
        History history = historyRepository.findById(historyId).get();
        historyTag.setHistory(history);
        return historyTagRepository.save(historyTag);
    }
}
