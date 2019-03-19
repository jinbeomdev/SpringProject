package com.wrapsody.demo.history.controller;

import com.wrapsody.demo.history.dto.RequestCreateHistoryDto;
import com.wrapsody.demo.history.dto.ResponseHistoryDto;
import com.wrapsody.demo.history.exception.ResourceNotFoundException;
import com.wrapsody.demo.wrapsody.exception.WrapsodyNotFoundException;
import com.wrapsody.demo.wrapsody.exception.WrapsodyUnauthorizedException;
import com.wrapsody.demo.history.*;
import com.wrapsody.demo.history.repository.HistoryAuthRepository;
import com.wrapsody.demo.history.repository.HistoryRepository;
import com.wrapsody.demo.history.repository.HistoryTagRepository;
import com.wrapsody.demo.wrapsody.RequestWrapsodySetAuth;
import com.wrapsody.demo.wrapsody.RequestWrapsodySetTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/history/all")
    public Page<ResponseHistoryDto> getAllHistory(@RequestParam String userId) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdAt"));
        Page<History> histories = historyRepository.findByHistoryMasterIdAndHistoryIsDeleted(userId, false, pageable);
        return histories.map(history -> {
            List<HistoryTag> tags = tagRepository.findByHistoryId(history.getId());
            List<HistoryAuth> auths = authRepository.findByHistoryId(history.getId());
            ResponseHistoryDto responseHistoryDto = new ResponseHistoryDto(history, tags, auths);
            return new ResponseHistoryDto(history, tags, auths);
        });
    }

    @GetMapping("/history")
    public List<ResponseHistoryDto> getTop20History(@RequestParam(value = "userId") String userId) {
        List<History> histories = historyRepository.findTop20ByHistoryMasterIdAndHistoryIsDeletedOrderByHistoryIsFavoriteDescAndCreatedAtDesc(userId, false);
        return histories.stream().map(history -> {
            List<HistoryTag> tags = tagRepository.findByHistoryId(history.getId());
            List<HistoryAuth> auths = authRepository.findByHistoryId(history.getId());
            return new ResponseHistoryDto(history, tags, auths);
        }).collect(Collectors.toList());
    }

    @PostMapping("/history")
    public ResponseHistoryDto createHistory(@Valid @RequestBody RequestCreateHistoryDto createHistoryDto) {
        History history = createHistoryDto.toHistoryEntity();
        return new ResponseHistoryDto(historyRepository.save(history),
                tagRepository.saveAll(createHistoryDto.toHistoryTagEntity(history)),
                authRepository.saveAll(createHistoryDto.toHistoryAuthEntity(history)));
    }

    @DeleteMapping("/history/{historyId}")
    public Boolean deletedHistory(@PathVariable(value = "historyId") Long historyId) {
        return historyRepository.findById(historyId).map(history -> {
            history.setHistoryIsDeleted(true);
            historyRepository.save(history);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("history Id " + historyId + " not found"));
    }

    @PutMapping("/history/{historyId}/favorite/{isFavorite}")
    public List<ResponseHistoryDto> alterFavorite(@PathVariable(value = "historyId") Long historyId,
                                                  @PathVariable(value = "isFavorite") Boolean isFavorite) {
        return historyRepository.findById(historyId).map(history -> {
            history.setHistoryIsFavorite(isFavorite);
            historyRepository.save(history);
            return getTop20History(history.getHistoryMasterId());
        }).orElseThrow(() -> new ResourceNotFoundException("history Id " + historyId + " not found"));
    }
}
