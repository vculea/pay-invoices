package ro.sheet;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataTO {
    private Integer rowIndex;
    private String date;
    private String type;
    private String description;
    private BigDecimal debit;
    private BigDecimal credit;
    private String status;
}
