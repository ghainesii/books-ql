package net.ghaines.booksql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BooksQlTest {

    @Autowired
    WebApplicationContext context;

    @Test
    void testQuery() {
        String query = "{hello{message}}";
        WebTestClient webTestClient =
                MockMvcWebTestClient.bindToApplicationContext(context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();

        HttpGraphQlTester tester = HttpGraphQlTester.create(webTestClient);

        tester.document(query)
                .execute()
                .path("hello.message").matchesJson("\"Hello GraphQL!\"");

    }
}
