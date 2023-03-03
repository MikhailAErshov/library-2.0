package com.example.library20.example;

import com.example.library20.book.BookDto;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApacheHttpTest {

    private static CloseableHttpClient client;

    @BeforeAll
    public static void setUp() {
        client = HttpClients.createDefault();
    }

    @Test
    public void shouldDeleteTest() throws IOException, URISyntaxException {
        HttpDelete delete = new HttpDelete("http://localhost:8081/book/delete");
        URI uri = new URIBuilder(delete.getURI())
                .addParameter("name", "value1")
                .build();
        //HttpEntity entity = new StringEntity("");
        delete.setURI(uri);
        CloseableHttpResponse response = client.execute(delete);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(204);
    }

    @Test
    public void shouldAddTest() throws IOException, URISyntaxException {
        HttpPost post = new HttpPost("http://localhost:8081/book/add");
        CloseableHttpResponse response = client.execute(post);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(response.getEntity().getContentType().getValue()).isEqualTo("application/json");
    }
}
