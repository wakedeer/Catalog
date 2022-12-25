package inno.tech.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductInfo {
    private JsonNode liveApiData;
    private JsonNode rateReport;
}
