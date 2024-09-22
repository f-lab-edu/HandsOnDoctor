package org.chat.handsondoctor.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.chat.handsondoctor.model.User;
import org.chat.handsondoctor.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

@SpringBootTest
public class DynamoDBConnectionTest {

    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper;
    private UserRepository userRepository;

    @Value("${amazon.aws.accessKey}")
    String accessKey;

    @Value("${amazon.aws.secretKey}")
    String secretKey;

    @BeforeEach
    public void setUp() {
        // DynamoDB 자격 증명
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        // DynamoDB 클라이언트 생성
        amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_NORTHEAST_2) // 서울 리전, 필요에 따라 변경
                .build();

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @Test
    public void testCreateAndRetrieveUser() {

        try {
            User user = User.builder()
                    .nickName("minWook")
                    .userId("yjg")
                    .role("user")
                    .build();
            userRepository.saveUser(user);
            System.out.println("DynamoDB connection test passed successfully!");
        } catch (Exception e) {
            fail("DynamoDB connection test failed: " + e.getMessage());
        }
    }
}
