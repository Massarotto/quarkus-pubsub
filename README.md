# Repository with producer and consumer of a Pub/Sub Topic.

## quarkus-producer

### Change this references before run the project:

At PubSubService.class change topicName (use a name that you've created at Pub/Sub) and change projectId with your projectId.

<img width="666" alt="image" src="https://github.com/user-attachments/assets/8d014ba8-1a02-43fc-a0de-aec160ec11c9" />


### Steps to run this application:

1 - Generate json credentials at GCP.

2 - Set GOOGLE_APPLICATION_CREDENTIALS as a environment variable and choose the JSON file generated at step 1.

## quarkus-consumer

### Change this references before run the project:

At ConsumerPull.class change subscriptionId (use a name that you've created at Pub/Sub) and change projectId with your projectId.

<img width="716" alt="image" src="https://github.com/user-attachments/assets/37a2840d-8d80-4c46-b9ae-a882f4c42453" />

### Steps to run this application:

1 - Generate json credentials at GCP.

2 - Set GOOGLE_APPLICATION_CREDENTIALS as a environment variable and choose the JSON file generated at step 1.
