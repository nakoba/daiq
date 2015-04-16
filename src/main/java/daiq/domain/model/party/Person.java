package daiq.domain.model.party;

import daiq.domain.model.shared.ContactInfo;
import daiq.domain.model.shared.FullName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class Person implements Party, Serializable {
    private FullName name;
    private ContactInfo contactInfo;
}
