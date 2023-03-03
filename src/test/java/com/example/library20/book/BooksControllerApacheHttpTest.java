package com.example.library20.book;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class BooksControllerApacheHttpTest {

    @Test
    public void shouldDeleteTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8081/book/add");
//        HttpEntity entity = new StringEntity("{}");
//        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
    }
}
