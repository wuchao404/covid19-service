package org.open.covid19.entity.jhu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 约翰霍普金斯-数据实体
 * @author wuchao
 */
@Data
public class JhuCase {
    @JsonProperty("country")
    private String countryName;

    @JsonProperty("province")
    private List<String> provinceList;

    @JsonProperty("timeline")
    private TimeLine timeLine;

    @Data
    class TimeLine {
        private Map<String, Long> cases;
        private Map<String, Long> deaths;
        private Map<String, Long> recovered;

    }

}
