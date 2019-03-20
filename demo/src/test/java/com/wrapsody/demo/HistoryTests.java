package com.wrapsody.demo;

import com.wrapsody.demo.history.repository.HistoryRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryTests {

    static final String SERVER_URL = "http://localhost:8080/history";
    static final String USER_ID = "wrapsodynewuser";
    static final String FAVORITE = "favorite";

    @Autowired
    HistoryRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getTop20HistoryTest() throws IOException {
        HttpUriRequest request =  new HttpGet(SERVER_URL + "?userId=" + USER_ID);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(200)
        );
    }

    @Test
    public void createHistoryTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(SERVER_URL);

        String json = "{\n" +
                "  \"historyMasterId\":\"wrapsodynewuser\",\n" +
                "  \"historyMasterName\":\"test\",\n" +
                "  \"historyPreSetName\":\"test\",\n" +
                "  \"historyViewAuthAllUsers\":\"false\",\n" +
                "  \"tags\":[\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"aaa\", \"historyTagType\":\"REQUIRED\"},\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"testtag1\", \"historyTagType\":\"CUSTOM\"},\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"testtag2\", \"historyTagType\":\"CUSTOM\"}\n" +
                "  ],\n" +
                "  \"auths\":[\n" +
                "    {\"historyAuthId\":\"lbj2\", \"historyAuthName\":\"test2\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"syong\", \"historyAuthName\":\"test3\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"genie\", \"historyAuthName\":\"test4\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"minji.\", \"historyAuthName\":\"test5\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"minji..\", \"historyAuthName\":\"test6\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"jinbeom\", \"historyAuthName\":\"test7\", \"historyAuthType\":\"READ\"}\n" +
                "  ]\n" +
                "\n" +
                "}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    @Test
    public void deletedHistoryTest() throws IOException{
        Long historyId = 1L;
        HttpUriRequest request = new HttpDelete(SERVER_URL + "/" + historyId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertThat(
                repository.findById(historyId).get().getHistoryIsDeleted(),
                equalTo(true)
        );
    }

    @Test
    public void alterFavoriteToTrueTest() throws IOException{
        Long historyId = 1L;
        boolean isFavorite = true;
        HttpUriRequest request = new HttpPut(SERVER_URL + "/" + historyId + "/" + FAVORITE + "/" + isFavorite);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertThat(
                repository.findById(historyId).get().getHistoryIsFavorite(),
                equalTo(true)
        );
    }

    @Test
    public void alterFavoriteToFalseTest() throws IOException{
        Long historyId = 1L;
        boolean isFavorite = false;
        HttpUriRequest request = new HttpPut(SERVER_URL + "/" + historyId + "/" + FAVORITE + "/" + isFavorite);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertThat(
                repository.findById(historyId).get().getHistoryIsFavorite(),
                equalTo(false)
        );
    }
}
