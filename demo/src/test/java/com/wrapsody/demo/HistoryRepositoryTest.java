package com.wrapsody.demo;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryRepositoryTest {

    @Autowired
    HistoryRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void getHistory() {
        repository.save(History.builder()
            .historyMasterName("김진범")
            .historyFreeSetName("템플릿")
            .build());

        List<History> HistoryList = repository.findAll();

        History history = HistoryList.get(0);
        assertThat(history.getHistoryMasterName(), is("김진범"));
        assertThat(history.getHistoryFreeSetName(), is("템플릿"));
    }

}
