package com.wrapsody.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HistoryTagController {
    private  HistoryRepository historyRepository;
    private  HistoryTagRepository historyTagRepository;

    HistoryTagController(HistoryRepository historyRepository, HistoryTagRepository historyTagRepository) {
        this.historyRepository = historyRepository;
        this.historyTagRepository = historyTagRepository;
    }

    @GetMapping("/history/{historyId}/tag")
    public List<HistoryTag> getAllHistoryTagByHistroyId(@PathVariable (value =  "historyId") Long historyId) {
        return historyTagRepository.findByHistoryHistoryId(historyId);
    }

    @PostMapping("/history/{historyId}/tag")
    public HistoryTag createHistoryTag(@PathVariable (value = "historyId") Long historyId,
                                       @Valid @RequestBody RequestCreateHistoryTagDto createHistoryTagDto) {
        History history = historyRepository.findById(historyId).get();
        return historyTagRepository.save(createHistoryTagDto.toEntity(history));
    }

    @PostMapping("history/{historyId}/tags")
    public List<HistoryTag> createHistoryTags(@PathVariable (value = "historyId") Long historyId,
                                              @Valid @RequestBody RequestCreateHistoryTagsDto createHistoryTagsDto) {
        return historyTagRepository.saveAll(createHistoryTagsDto.toEntity());
    }
}
