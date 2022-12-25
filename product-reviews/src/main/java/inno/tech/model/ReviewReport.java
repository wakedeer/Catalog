package inno.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewReport {

    private long count;
    private double avgScore;

}
