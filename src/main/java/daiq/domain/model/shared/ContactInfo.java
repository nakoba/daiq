package daiq.domain.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class ContactInfo implements Serializable {
    private EmailAddress emailAddress;
    private PostalAddress postalAddress;
    private Tel primaryTel;
    private Tel secondaryTel;
}
