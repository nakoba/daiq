package daiq.domain.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class FullName implements Serializable {
    
    private String familyName;
    private String givenName;

    public FullName changedFamilyNameOf(String familyName) {
        return new FullName(familyName, getGivenName());
    }
    public FullName changedGivenNameOf(String givenName) {
        return new FullName(getFamilyName(), givenName);
    }
}
