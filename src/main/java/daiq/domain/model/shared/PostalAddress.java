package daiq.domain.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostalAddress implements Serializable {
    private PostalCode postalCode;
    private String city;
    private String stateProvince;
    private String streetAddress;
}
