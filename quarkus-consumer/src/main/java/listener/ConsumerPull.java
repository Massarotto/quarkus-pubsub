package listener;

import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.stub.GrpcSubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStubSettings;
import com.google.pubsub.v1.*;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class ConsumerPull {

    private final String subscriptionId = "customerTopic-sub";
    private final String projetcId = "study-464117";

    @Scheduled(every = "5s")
    void consumeMessage() {
        try {
            ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projetcId, subscriptionId);
            SubscriberStubSettings subscriberStubSettings = SubscriberStubSettings.newBuilder().build();
            try (GrpcSubscriberStub subscriber = GrpcSubscriberStub.create(subscriberStubSettings)) {
                PullRequest pullRequest = PullRequest.newBuilder()
                        .setMaxMessages(10)
                        .setSubscription(subscriptionName.toString())
                        .build();

                PullResponse pullResponse = subscriber.pullCallable().call(pullRequest);

                for (ReceivedMessage message : pullResponse.getReceivedMessagesList()) {
                    Log.info("Message received: " + message.getMessage().getData().toStringUtf8());

                    //Received confirm
                    AcknowledgeRequest acknowledgeRequest = AcknowledgeRequest.newBuilder()
                            .setSubscription(subscriptionName.toString())
                            .addAckIds(message.getAckId())
                            .build();
                    subscriber.acknowledgeCallable().call(acknowledgeRequest);
                }
            }
        } catch (Exception e) {
            Log.error("Error on consume message: " + e.getMessage());
        }
    }

}
