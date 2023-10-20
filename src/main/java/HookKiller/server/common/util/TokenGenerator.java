package HookKiller.server.common.util;

import java.util.UUID;

public class TokenGenerator {

    public static String generateUniqueToken() {
        return UUID.randomUUID().toString();
    }
}
