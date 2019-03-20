package com.wrapsody.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void requestWrapsodyDocumentWithBadSyncId() throws IOException {
        String syncId = "1";
        HttpUriRequest request = new HttpGet("http://localhost:8080/wrapsody/document/syncId=" + syncId);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(404)
        );
    }
}
