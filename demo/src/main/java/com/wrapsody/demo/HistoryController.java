package com.wrapsody.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class HistoryController {

    private HistoryRepository historyRepository;
    private HistoryTagRepository tagRepository;
    private HistoryAuthRepository authRepository;

    HistoryController(HistoryRepository repository,
                      HistoryTagRepository tagRepository,
                      HistoryAuthRepository authRepository) {
        this.historyRepository = repository;
        this.tagRepository = tagRepository;
        this.authRepository = authRepository;
    }

    @GetMapping("/history/test")
    public Page<ResponseAllHistoryDto> getAllHistory() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdAt"));
        Page<History> histories = historyRepository.findAll(pageable);
        return histories.map(history -> {
            List<HistoryTag> tags = tagRepository.findByHistoryHistoryId(history.getHistoryId());
            List<HistoryAuth> auths = authRepository.findByHistoryHistoryId(history.getHistoryId());
            ResponseAllHistoryDto responseAllHistoryDto = new ResponseAllHistoryDto(history, tags, auths);
            return responseAllHistoryDto;
        });
    }

    @GetMapping("/history")
    public Page<History> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable);
    }


    @PostMapping("history")
    public History createHistory(@Valid @RequestBody RequestCreateHistoryDto createHistoryDto) {
        return historyRepository.save(createHistoryDto.toEntity());
    }

    @PostMapping("history/test")
    public History createHistoryTest(@Valid @RequestBody RequestCreateHistoryDto createHistoryDto) {
        log.info(createHistoryDto.toString());
        History history = createHistoryDto.toHistoryEntity();
        history = historyRepository.save(history);
        tagRepository.saveAll(createHistoryDto.toHistoryTagEntity(history));
        authRepository.saveAll(createHistoryDto.toHistoryAuthEntity(history));
        return history;
    }
}
