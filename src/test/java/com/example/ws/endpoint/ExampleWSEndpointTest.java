package com.example.ws.endpoint;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;

@ContextConfiguration("classpath:springws-endpoint-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleWSEndpointTest {

    private MockWebServiceClient mockClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testSendEmailMessages() throws IOException {
        mockClient.sendRequest(withSoapEnvelope(applicationContext.getResource("exampleWSRequest.xml")))
            .andExpect(noFault());
    }
}
