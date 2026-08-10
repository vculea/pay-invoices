package ro.sheet;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PeopleTO {
    private String fullName;
    private List<String> names;
    private List<String> donations;
    private String note;
}
