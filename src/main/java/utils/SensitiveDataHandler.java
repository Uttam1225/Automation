package utils;

import org.json.JSONObject;

public class SensitiveDataHandler {

    public FetchAWSCreds fetechAwsCreds() {
        SecretsManagerUtil secretsManager = new SecretsManagerUtil("MyApp/Naukri.com/Credentials", "us-east-1");
        JSONObject secret = secretsManager.getSecret();
        String first = secret.getString("password");
        String second = secret.getString("name");
        return new FetchAWSCreds(first, second);
    }
    
}
