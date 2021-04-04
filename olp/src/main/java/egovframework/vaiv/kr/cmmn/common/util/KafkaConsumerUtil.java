package egovframework.vaiv.kr.cmmn.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * Kafka : Kafka Consumer util
 * @category 공통
 * @author kmk
 * @since 2021-03-02
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.02    kmk           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2021 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class KafkaConsumerUtil {
//	public static void main(String[] args) {
//		consumerGetOffset("GameLog", 50);   // patition 0 하나일 때
//		consumerGetOffset("GameLog", 0, 1); // patition 여러개일 때
//	}

//	public static void consumerGetOffset(String topicName, int offSetNum) {  patition 0 하나일 때
	public static void consumerGetOffset(String topicName, int offSetNum, int partitionNum) {  // patition 여러개일 때
		Properties props = new Properties();
		props.put("bootstrap.servers", "1.221.237.242:9093"); // kafka 클러스터 연결하기위한 리스트 정보 , 로 구분하여 표시
		props.put("session.timeout.ms", "10000");             // session 설정
		props.put("group.id", "KafkaConsumer");                // groupId 설정
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
//		props.put("enable.auto.commit", "true");
//		props.put("auto.commit.interval.ms", "1000");
//		props.put("session.timeout.ms", "60000");
		
//    	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    	try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);){
            //토픽리스트를 매개변수로 준다. 구독신청을 한다.
    		List partitionList = new ArrayList();
    		for(int i=0; i<partitionNum; i++) {
    			partitionList.add(new TopicPartition(topicName, i));
    		}
            TopicPartition partition0 = new TopicPartition(topicName, 0);
//            TopicPartition partition1 = new TopicPartition(topicName, 1);
            consumer.assign(Arrays.asList(partition0));
//    		consumer.assign(partitionList);
            // 파티션의 offSetNum번 오프셋의 메시지를 가져와라
    		for(int j=0; j<partitionList.size(); j++) {
    			consumer.seek((TopicPartition) partitionList.get(j), offSetNum);
    		}
//    		consumer.seekToBeginning(partitionList);
//            consumer.seek(partition0, offSetNum);
//            consumer.seek(partition1, offSetNum);
            while (true) {
              //컨슈머는 토픽에 계속 폴링하고 있어야한다. 아니면 브로커는 컨슈머가 죽었다고 판단하고, 해당 파티션을 다른 컨슈머에게 넘긴다.
              ConsumerRecords<String, String> records = consumer.poll(500);
              for (ConsumerRecord<String, String> record : records) {
                  String s = record.topic();
                  if (topicName.equals(s)) {
                      System.out.println(record.value());
                  } else {
                      throw new IllegalStateException("get message on topic " + record.topic());
                  }
              }
              
//               * DB로직(CRUD) 이후 커밋.
               
//              consumer.commitSync();
            }
        } finally {}
	}
}
