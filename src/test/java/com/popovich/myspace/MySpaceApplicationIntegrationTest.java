package com.popovich.myspace;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.repository.MasterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MySpaceApplicationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MasterRepository repository;

    @Test
    void testCanGetSlackers() {
        repository.save(new Master("Raven", 1000));

        var json = restTemplate.getForObject("/api/masters/slackers", String.class);
        assertThat(json, containsString("Raven"));
    }
}
