package com.oumoi.virtualthreads;

import com.oumoi.virtualthreads.config.RabbitPaymentBatchMessaging;
import com.oumoi.virtualthreads.domain.PaymentBatch;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static java.lang.System.out;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public void run(String... args) throws Exception {

        for (int i = 1 ; i < 1000000; i++){
            PaymentBatch paymentBatch = new PaymentBatch();
            paymentBatch.setBatchId(UUID.randomUUID().toString());
            paymentBatch.setPostedOn(String.valueOf(new Date()));
            paymentBatch.setBatchNo("CT00"+i);
            paymentBatch.setBatchAmount(100000.00);

            rabbitTemplate.convertAndSend(RabbitPaymentBatchMessaging.EXCHANGE,
                    RabbitPaymentBatchMessaging.VIRTUAL_ROUTING_KEY,paymentBatch);
            out.println("Batch posted successfully.");
        }
    }
}
