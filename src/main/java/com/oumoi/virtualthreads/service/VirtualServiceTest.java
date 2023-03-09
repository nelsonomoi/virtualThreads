package com.oumoi.virtualthreads.service;

import com.oumoi.virtualthreads.config.RabbitPaymentBatchMessaging;
import com.oumoi.virtualthreads.domain.PaymentBatch;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service

public class VirtualServiceTest {


    @RabbitListener(queues = RabbitPaymentBatchMessaging.QUEUE,containerFactory = "simpleRabbitListenerContainerFactory")
    public void listener(PaymentBatch paymentBatch){
        printTaskExecuted(paymentBatch);
    }

    @RabbitListener(queues = RabbitPaymentBatchMessaging.QUEUE,containerFactory = "simpleRabbitListenerContainerFactory")
    public void listener2(PaymentBatch paymentBatch){
//        printTaskExecuted(paymentBatch);
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                printTaskExecuted(paymentBatch);
            }
        });
    }

    public void printTaskExecuted(PaymentBatch paymentBatch){
//        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("I am running a thread "+Thread.currentThread() + "payment batch "+
                    paymentBatch.getBatchNo()+"  " +paymentBatch.getBatchAmount());
//                }
        }

//            });
//        }
    }
}
