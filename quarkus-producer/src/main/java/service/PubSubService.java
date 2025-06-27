package service;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PubSubService {

    private final String topicName = "customerTopic";
    private final String projetcId = "study-464117";

    public void publishMessage(String message) {
        try {
            TopicAdminClient topicAdminClient = TopicAdminClient.create();
            ProjectTopicName projectTopicName = ProjectTopicName.of(projetcId, topicName);
            Publisher publisher = Publisher.newBuilder(projectTopicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubSubMessage = PubsubMessage.newBuilder().setData(data).build();

            publisher.publish(pubSubMessage);
            Log.info("Message sent: " + message);
        } catch (Exception e) {
            Log.error("Error on publish messagm: " + e.getMessage());
        }
    }

}
