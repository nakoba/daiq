package daiq.domain.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class Tel implements Serializable {
    private TelType telType = TelType.FIXED;
    private String number;
}
