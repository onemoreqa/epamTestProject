package les22.retrofit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Register {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Register setEmail(String email) {
        this.email = email;
        return this;
    }

    public Register setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Register{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
