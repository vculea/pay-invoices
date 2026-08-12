package ro.sheet;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogTO {
    private BigDecimal value;
    private String nameCont;
}
