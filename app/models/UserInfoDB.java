package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

import java.util.HashMap;
import java.util.Map;


public class UserInfoDB {

    private static Map<String, UserInfo> userinfos = new HashMap<String, UserInfo>();


    /**
     * Returns true if the email represents a known user.
     * @param email The email.
     * @return True if known user.
     */
    public static boolean isUser(String email) {
        Transaction txn = Ebean.beginTransaction();
        UserInfo info1 =UserInfo.find.byId(email);
        try {
            if (info1 == null) {
               // System.out.println("it is null in isUser");
            }

            if (info1 != null) {
                if(info1.getStatus().equals("active")) {
                    if (info1.getUserId().equals(email))
                        return true;
                }
                else
                    return false;
            }
        } finally {
            txn.commit();
            txn.end();
        }
       return false;
       // return userinfos.containsKey(email);
    }

    /**
     * Returns the UserInfo associated with the email, or null if not found.
     * @param email The email.
     * @return The UserInfo.
     */
    public static UserInfo getUser(String email) {
        UserInfo info1 =UserInfo.find.byId(email);
        if (info1 != null) {
            if(info1.getUserId().equals(email))
                return info1;
            else
                return null;
        }
        return null;
        //return userinfos.get((email == null) ? "" : email);
    }

    /**
     * Returns true if email and password are valid credentials.
     * @param email The email.
     * @param password The password.
     * @return True if email is a valid user email and password is valid for that email.
     */
    public static boolean isValid(String email, String password) {
        return ((email != null)
                &&
                (password != null)
                &&
                isUser(email)
                &&
                getUser(email).getPassword().equals(password));
    }



}
