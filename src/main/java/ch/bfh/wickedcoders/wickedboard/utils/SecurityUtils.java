package ch.bfh.wickedcoders.wickedboard.utils;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * Created by chris on 28.10.14.
 */
public class SecurityUtils {
    private static final int CPU_COST = 16384;
    private static final int MEMORY_COST = 8;
    private static final int PARALLELIZATION = 1;

    public static String getHashedPassword(String password) {
        return SCryptUtil.scrypt(password, CPU_COST, MEMORY_COST, PARALLELIZATION);
    }

    /**
     * Checks whether given plaintext password corresponds
     * to a stored salted hash of the password.
     */
    public static boolean checkPassword(String password, String hashed) {
        return SCryptUtil.check(password, hashed);
    }
}
