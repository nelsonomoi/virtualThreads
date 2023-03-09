# virtualThreads
Using virtual Threads to increase performance of a rabbit queueing system.


## notes

**factory.setMessageConverter(messageConverter())**; sets the message converter to be used by the factory. The messageConverter() method should return a bean of type MessageConverter.

**factory.setConcurrentConsumers(10)**; sets the initial number of concurrent consumers that will be created by the factory.

**factory.setMaxConcurrentConsumers(50)**; sets the maximum number of concurrent consumers that can be created by the factory.

**factory.setTaskExecutor(Executors.newFixedThreadPool(50))**; sets the task executor to be used by the factory. In this case, it creates a fixed thread pool with 50 threads.

**factory.setPrefetchCount(500)**; sets the prefetch count for the factory. This controls how many messages can be pre-fetched from the broker at a time.

**factory.setBatchSize(500)**; sets the batch size for the factory. This controls how many messages can be processed in a single batch.


The line **factory.setTaskExecutor(Executors.newFixedThreadPool(50));** is important because it sets the TaskExecutor to be used by the SimpleRabbitListenerContainerFactory.

The **TaskExecutor** is responsible for executing the actual message processing logic in a separate thread. By default, the **SimpleMessageListenerContainer** (which is created by the SimpleRabbitListenerContainerFactory) will use a SyncTaskExecutor which executes the message processing logic in the same thread that called the listener. This can lead to slow message processing, as the listener thread will be blocked until the message processing logic is complete.

By setting a **TaskExecutor**, the message processing logic can be executed in a separate thread, allowing the listener thread to immediately return to the RabbitMQ broker to fetch more messages. This can help improve the overall throughput and responsiveness of the application.

In this code snippet, the TaskExecutor is created using **Executors.newFixedThreadPool(50)**, which creates a fixed-size thread pool with 50 threads. This means that up to 50 messages can be processed concurrently. The actual number of concurrent messages being processed will depend on the concurrentConsumers and maxConcurrentConsumers settings of the **SimpleRabbitListenerContainerFactory**

### prefetch count
Setting a prefetch count is important because it determines how many messages can be pre-fetched from the broker and held by the consumer before they are processed. This can help improve the efficiency of message consumption, as it allows the consumer to process messages more quickly without waiting for new messages to be fetched.

If the prefetch count is set too high, it can cause the consumer to consume more messages than it can handle, leading to resource exhaustion and slow processing. On the other hand, if the prefetch count is set too low, it can cause the consumer to spend too much time waiting for new messages to arrive, which can also lead to slow processing.

In general, the optimal prefetch count will depend on the specific use case and workload of the application. A good rule of thumb is to set the prefetch count to a value that is high enough to keep the consumer busy without overwhelming it with too many messages to process at once.
