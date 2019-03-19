package com.wrapsody.demo;

import com.wrapsody.demo.history.repository.HistoryAuthRepository;
import com.wrapsody.demo.history.repository.HistoryRepository;
import com.wrapsody.demo.history.repository.HistoryTagRepository;
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
/*            History history = History.builder().historyMasterName("김진범").historyMasterId("jinbeom").historyPreSetName("김진범_템플릿").historyIsDeleted(false).build();
            HistoryTag historyTag1 = HistoryTag.builder().historyTagName("보안").historyTagType(HistoryTagType.REQUIRED).build();
            historyTag1.setHistory(history);
            HistoryTag historyTag2 = HistoryTag.builder().historyTagName("신입사원").historyTagType(HistoryTagType.SELECTION).build();
            historyTag2.setHistory(history);
            HistoryAuth historyAuth1 = HistoryAuth.builder().historyAuthName("김진규").historyAuthType(HistoryAuthType.REVISION).build();
            historyAuth1.setHistory(history);
            HistoryAuth historyAuth2 = HistoryAuth.builder().historyAuthName("조상원").historyAuthType(HistoryAuthType.REVISION).build();
            historyAuth2.setHistory(history);
            HistoryAuth historyAuth3 = HistoryAuth.builder().historyAuthName("조윤정").historyAuthType(HistoryAuthType.REVISION).build();
            historyAuth3.setHistory(history);
            HistoryAuth historyAuth4 = HistoryAuth.builder().historyAuthName("허태경").historyAuthType(HistoryAuthType.REVISION).build();
            historyAuth4.setHistory(history);

            log.info("Preloading " + historyRepository.save(history));
            log.info("Preloading " + tagRepository.save(historyTag1));
            log.info("Preloading " + tagRepository.save(historyTag2));
            log.info("Preloading " + authRepository.save(historyAuth1));
            log.info("Preloading " + authRepository.save(historyAuth2));
            log.info("Preloading " + authRepository.save(historyAuth3));
            log.info("Preloading " + authRepository.save(historyAuth4));*/
        };
    }
}
