package fr.web.recipy.tools;

import org.mindrot.jbcrypt.BCrypt;


public class HashTool {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password_defined, String password_hashed) {
        return BCrypt.checkpw(password_defined, password_hashed);
    }
}
