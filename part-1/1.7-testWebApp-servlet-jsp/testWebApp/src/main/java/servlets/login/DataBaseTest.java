package servlets.login;

import utills.FormValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBaseTest {
    private Map<String,Map<String,String>> userLoginDataBase;
    private final ReadWriteLock readWriteLock;
    private static DataBaseTest dataBase;
    private final String validMsg;
    private DataBaseTest (){
        this.readWriteLock = new ReentrantReadWriteLock();
        this.userLoginDataBase = new HashMap<>();
        this.validMsg ="<span class=\"valid-message\">ok</span>";
    }

    public static synchronized DataBaseTest getDataBase (){
        if (dataBase == null) {
            dataBase = new DataBaseTest();
        }
        return dataBase;
    }

    public boolean isUserNameFree(String userName){
            return !(userLoginDataBase.containsKey(userName));
    }

    public boolean isMailFree(String mail) {
            for (Map.Entry<String, Map<String, String>> stringMapEntry : userLoginDataBase.entrySet()) {
                if (stringMapEntry.getValue().containsValue(mail)) return false;
            }
            return true;

    }


    public void addToDataBase(String userName, String pass, String mail){
        try {
            readWriteLock.writeLock().lock();
            Map<String,String> userParamMap = new HashMap<>();
            if (getAnswerUserName(userName).equals(validMsg)
                    && getAnswerUserPass(pass).equals(validMsg)
                    && getAnswerEmail(mail).equals(validMsg)) {
                userParamMap.put("password", pass);
                userParamMap.put("mail", mail);
                userLoginDataBase.put(userName, userParamMap);
            }
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }


    public String getAnswerUserName(String userName) {
        if (!(FormValidator.validateString(userName))) {
            return "<span class=\"invalid-message\">login can't be empty!</span>";
        } else if (!(isUserNameFree(userName))) {
            return "<span class=\"invalid-message\">login already use</span>";
        } else return validMsg;

    }

    public String getAnswerUserPass(String userPass) {
        if (!(FormValidator.validateString(userPass))) {
            return "<span class=\"invalid-message\">password can't be empty!</span>";
        } else if (userPass.length() < 5) {
            return "<span class=\"invalid-message\">to small password</span>";
        } else return validMsg;
    }

    public String getAnswerEmail(String mail) {
        if (!(FormValidator.validateString(mail))) {
            return "<span class=\"invalid-message\">e-mail can't be empty!</span>";
        }else if (!(isMailFree(mail))){
            return "<span class=\"invalid-message\">e-mail already use</span>";
        } else if (FormValidator.validateEmail(mail)) return validMsg;

        return "<span class=\"invalid-message\">incorrect e-mail</span>";
    }


}
