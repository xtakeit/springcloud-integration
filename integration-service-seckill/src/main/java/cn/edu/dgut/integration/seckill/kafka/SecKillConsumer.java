package cn.edu.dgut.integration.seckill.kafka;


import cn.edu.dgut.integration.seckill.service.SecKill2Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SecKillConsumer {

    @Resource
    private SecKill2Service secKill2Service;

    /**
     * kafka消费组不知道如何构建,同时开启多个消费者
     * @param record
     */

    //    @KafkaListener(id = "secKill-0",topics = {"secKill"})
    @KafkaListener(id = "secKill-0", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"0"})})
    public void handleSecKill(ConsumerRecord<?, ?> record) {
        // 获取消息队列中的 用户id和库存id
        handle(record);
    }

    @KafkaListener(id = "secKill-1", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"1"})})
    public void handleSecKill2(ConsumerRecord<?, ?> record) {
        handle(record);
    }

    @KafkaListener(id = "secKill-2", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"2"})})
    public void handleSecKill3(ConsumerRecord<?, ?> record) {
        handle(record);
    }

    @KafkaListener(id = "secKill-3", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"3"})})
    public void handleSecKill4(ConsumerRecord<?, ?> record) {
        // 获取消息队列中的 用户id和库存id
        handle(record);
    }

    @KafkaListener(id = "secKill-4", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"4"})})
    public void handleSecKill5(ConsumerRecord<?, ?> record) {
        handle(record);
    }

    @KafkaListener(id = "secKill-5", groupId = "secKill-group",
            topicPartitions = {@TopicPartition(topic = "secKill2", partitions = {"5"})})
    public void handleSecKill6(ConsumerRecord<?, ?> record) {
        handle(record);
    }



    private void handle(ConsumerRecord<?, ?> record){
        // 获取消息队列中的 用户id和库存id
        String[] message = record.value().toString().split(":");
        log.info("kill:"+ record.partition() + ":" + message[0] + ":" + message[1]);
        Long userId = Long.parseLong(message[0]);
        Long storageId = Long.parseLong(message[1]);
        secKill2Service.kill(userId, storageId);
    }

}
