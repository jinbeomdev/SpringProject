package com.wrapsody.demo;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class HistoryController {

    private HistoryRepository repository;

    HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/history")
    public Page<History> getAllHistory(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping("history")
    public History createHistory(@Valid @RequestBody History history) {
        return repository.save(history);
    }
}
