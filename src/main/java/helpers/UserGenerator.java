package helpers;

import com.ibm.icu.text.Transliterator;
import config.UsersDataConfig;
import enums.UsersData;
import org.aeonbits.owner.ConfigFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

public class UserGenerator {
    private static final Map<UsersData, String> USER = new EnumMap<>(UsersData.class);
    private static final String DEFINING_LOGIN=System.getProperty("otus_login");
    private static final String DEFINING_PASSWORD=System.getProperty("otus_password");
    private String login;
    private String password;

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLogin(String login) {
        USER.put(UsersData.LOGIN, login);
        this.login = login;
    }

    public void setPassword(String password) {
        USER.put(UsersData.PASSWORD, password);
        this.password = password;
    }

    public Map<UsersData, String> getUser() {
        return USER;
    }

    public void setUser() {
        setDefaultUserData();
    }

    private void setCredentials(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    private void setDefaultUserData() {

        Properties props = new Properties();
        if (DEFINING_LOGIN != null) {
            props.setProperty("otus_login", DEFINING_LOGIN);
        }
        if (DEFINING_PASSWORD != null) {
            props.setProperty("otus_password", DEFINING_PASSWORD);
        }
        UsersDataConfig cfg = ConfigFactory.create(UsersDataConfig.class, props);

        setCredentials(cfg.otusLogin(), cfg.otusPassword());
        USER.put(UsersData.FIRST_PHONE, GenerationTools.getRandomPhone());
        USER.put(UsersData.SECOND_PHONE, GenerationTools.getRandomPhone());
        USER.put(UsersData.THIRD_PHONE, GenerationTools.getRandomPhone());
        }
}