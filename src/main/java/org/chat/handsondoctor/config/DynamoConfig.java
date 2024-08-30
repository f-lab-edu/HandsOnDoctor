package org.chat.handsondoctor.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoConfig {

    // code 레벨에서 해당 값을 저장하는 방법이 맞는가?
    private static final String AWS_ACCESS_KEY = "";
    private static final String AWS_SECRET_KEY = "";
    private static final String AWS_REGION = "";

    public static DynamoDBMapper createDynamoDBMapper() {
        return new DynamoDBMapper(createAmazonDynamoDB(), DynamoDBMapperConfig.DEFAULT);
    }

    private static AmazonDynamoDB createAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "dynamodb" + AWS_REGION + ".amazonaws.com", AWS_REGION))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)))
                .build();
    }
}
