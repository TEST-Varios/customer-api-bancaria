package cl.customer.customerapi.config;

import lombok.Data;

@Data
public class Constants {

    private Constants() {
    }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String SPRING_PROFILE_TEST = "test";

    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String UTF_UNICODE = "UTF-8";
}
