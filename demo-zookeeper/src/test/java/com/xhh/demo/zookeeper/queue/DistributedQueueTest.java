package com.xhh.demo.zookeeper.queue;

import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * 队列
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/17 上午10:45
 * @since 1.6
 */
@Slf4j
public class DistributedQueueTest {

    /** 队列节点 */
    private static final String PATH = "/example/queue";
    
    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        DistributedQueue<String> queue = null;
        try {
            client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR, new ExponentialBackoffRetry(1000, 3));
            client.getCuratorListenable().addListener(new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                    log.debug("CuratorEvent: {}", event.getType().name());
                }
            });
            client.start();
            QueueConsumer<String> consumer = createQueueConsumer();
            QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), PATH);
            queue = builder.buildQueue();
            queue.start();
            for (int i = 0; i < 10; i++) {
                queue.put(" test-" + i);
                Thread.sleep((long)(3 * Math.random()));
            }
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(queue);
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 序列化
     *
     * @return 序列化队列
     */
    private static QueueSerializer<String> createQueueSerializer() {
        return new QueueSerializer<String>(){
            @Override
            public byte[] serialize(String item) {
                return item.getBytes();
            }
            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }
        };
    }

    /**
     * 消费者
     *
     * @return 队列消费者
     */
    private static QueueConsumer<String> createQueueConsumer() {
        return new QueueConsumer<String>(){
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.debug("connection new state: {}", newState.name());
            }
            @Override
            public void consumeMessage(String message) throws Exception {
                log.debug("consume one message: {}", message);
            }
        };
    }
}
