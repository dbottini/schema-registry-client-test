package com.example.demo.packag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
public class TestComponent {
    private static final Logger log = LoggerFactory.getLogger(TestComponent.class);

    public TestComponent() {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .build();
        log.error("SqsClient started successfully.");
    }
}
