package pkq.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pkq.Goddess;

import javax.swing.text.html.Option;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Men {
    private Optional<Goddess> gddes=Optional.empty();


}
