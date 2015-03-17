package com.xhh.demo.zookeeper.queue;

import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * 队列
 *
 * <p>可以为队列中的每一个元素设置一个ID，再通过ID把队列中任意的元素移除。
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/17 上午10:45
 * @since 1.6
 */
@Slf4j
public class DistributedIdQueueTest {

    /** 队列节点 */
    private static final String PATH = "/example/queue";
    
    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        DistributedIdQueue<String> queue = null;
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
            queue = builder.buildIdQueue();
            queue.start();
            for (int i = 0; i < 10; i++) {
                queue.put(" test-" + i, "Id" + i);
                Thread.sleep((long)(50 * Math.random()));
                queue.remove("Id" + i);
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
