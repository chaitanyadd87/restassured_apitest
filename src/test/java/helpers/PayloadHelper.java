package helpers;

import java.util.HashMap;
import java.util.Map;

public class PayloadHelper {
    public static Map<String, String> getLoginPayload(String email, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }
}