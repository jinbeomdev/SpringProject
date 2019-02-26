package com.wrapsody.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(HistoryRepository historyRepository,
                 HistoryAuthRepository authRepository,
                 HistoryTagRepository tagRepository) {
        return args -> {
            History history = History.builder().historyMasterName("김진범").historyPreSetName("김진범_템플릿").historyIsDeleted(true).build();
            HistoryTag historyTag1 = HistoryTag.builder().historyTagName("보안").build();
            historyTag1.setHistory(history);
            HistoryTag historyTag2 = HistoryTag.builder().historyTagName("신입사원").build();
            historyTag2.setHistory(history);
            HistoryAuth historyAuth1 = HistoryAuth.builder().historyAuthName("김진규").historyAuthType(true).build();
            historyAuth1.setHistory(history);
            HistoryAuth historyAuth2 = HistoryAuth.builder().historyAuthName("조상원").historyAuthType(true).build();
            historyAuth2.setHistory(history);
            HistoryAuth historyAuth3 = HistoryAuth.builder().historyAuthName("조윤정").historyAuthType(true).build();
            historyAuth3.setHistory(history);
            HistoryAuth historyAuth4 = HistoryAuth.builder().historyAuthName("허태경").historyAuthType(true).build();
            historyAuth4.setHistory(history);

            log.info("Preloading " + historyRepository.save(history));
            log.info("Preloading " + tagRepository.save(historyTag1));
            log.info("Preloading " + tagRepository.save(historyTag2));
            log.info("Preloading " + authRepository.save(historyAuth1));
            log.info("Preloading " + authRepository.save(historyAuth2));
            log.info("Preloading " + authRepository.save(historyAuth3));
            log.info("Preloading " + authRepository.save(historyAuth4));
        };
    }
}
