package com.wrapsody.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class HistoryController {
    private HistoryRepository repository;

    HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/history")
    List<History> all(@PathVariable Long syncId) {
        return repository.findAll();
    }

    @PostMapping("/history")
    void saveHistory(@RequestBody History newHistory) {
        log.info("saving " + repository.save(newHistory));
    }
}
