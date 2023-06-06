package com.example.websocketclient;

import com.example.websocketclient.client.WebSocketClientExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessorJob {

    Logger logger = LoggerFactory.getLogger(ProcessorJob.class);


    @Scheduled(fixedRateString = "100000000")
    public void execute() {
        logger.info("execute()");

        WebSocketClientExample client = new WebSocketClientExample();
        client.connect();

        // Simulate sending a name after a delay of 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.sendName("John Doe");
    }


}
