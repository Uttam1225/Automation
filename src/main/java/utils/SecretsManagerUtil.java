package utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import org.json.JSONObject;

public class SecretsManagerUtil {

    private final String secretName;
    private final String region;

    public SecretsManagerUtil(String secretName, String region) {
        this.secretName = secretName;
        this.region = region;
    }
    public JSONObject getSecret() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(region)
                .build();

        // Retrieve the secret
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        // Get the secret value
        String secret = getSecretValueResult.getSecretString();
        return new JSONObject(secret);
    }
}
