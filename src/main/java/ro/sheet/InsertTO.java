package ro.sheet;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InsertTO {
    private String type;
    private String subtype;
    private BigDecimal value;
    private String status;
    private Integer rowIndex;
    private String nameCont;
}
