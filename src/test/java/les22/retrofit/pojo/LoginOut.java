package les22.retrofit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginOut {

    @JsonProperty("id")
    private String token;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "LoginOut{" +
                "token='" + token + '\'' +
                '}';
    }
}
