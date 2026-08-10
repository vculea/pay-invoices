package ro.sheet;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheltuieliTO {
    private String category;
    private List<String> values;
}
