package cl.customer.customerapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Data
public class ResponseCustomer {

    @JsonProperty("ID_Client")
    public UUID uuid = UUID.randomUUID();

    @Temporal(TemporalType.DATE)
    @JsonProperty("created")
    public Date created;

    @Temporal(TemporalType.DATE)
    @JsonProperty("modified")
    public Date modified;

    @Temporal(TemporalType.DATE)
    @JsonProperty("last_login")
    public Date lastLogin;

    public String token;

    @JsonProperty("is_active")
    public Boolean isActive;

}