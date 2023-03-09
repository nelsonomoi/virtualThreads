# virtualThreads
Using virtual Threads to increase performance of a rabbit queueing system.


## notes

**factory.setMessageConverter(messageConverter())**; sets the message converter to be used by the factory. The messageConverter() method should return a bean of type MessageConverter.

**factory.setConcurrentConsumers(10)**; sets the initial number of concurrent consumers that will be created by the factory.

**factory.setMaxConcurrentConsumers(50)**; sets the maximum number of concurrent consumers that can be created by the factory.

**factory.setTaskExecutor(Executors.newFixedThreadPool(50))**; sets the task executor to be used by the factory. In this case, it creates a fixed thread pool with 50 threads.

**factory.setPrefetchCount(500)**; sets the prefetch count for the factory. This controls how many messages can be pre-fetched from the broker at a time.

**factory.setBatchSize(500)**; sets the batch size for the factory. This controls how many messages can be processed in a single batch.

### prefetch count
Setting a prefetch count is important because it determines how many messages can be pre-fetched from the broker and held by the consumer before they are processed. This can help improve the efficiency of message consumption, as it allows the consumer to process messages more quickly without waiting for new messages to be fetched.

If the prefetch count is set too high, it can cause the consumer to consume more messages than it can handle, leading to resource exhaustion and slow processing. On the other hand, if the prefetch count is set too low, it can cause the consumer to spend too much time waiting for new messages to arrive, which can also lead to slow processing.

In general, the optimal prefetch count will depend on the specific use case and workload of the application. A good rule of thumb is to set the prefetch count to a value that is high enough to keep the consumer busy without overwhelming it with too many messages to process at once.
