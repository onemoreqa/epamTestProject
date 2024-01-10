package les22.retrofit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterOut {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("token")
    private String token;

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RegisterOut{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
