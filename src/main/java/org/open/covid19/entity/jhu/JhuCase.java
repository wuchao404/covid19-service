package org.open.covid19.entity.jhu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.open.covid19.entity.Case;
import org.open.covid19.utils.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 约翰霍普金斯-实体
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
    public class TimeLine {
        private Map<String, Long> cases;
        private Map<String, Long> deaths;
        private Map<String, Long> recovered;

        public Map<String, Long> getCases() {
            return castDate(cases);
        }

        public Map<String, Long> getDeaths() {
            return castDate(deaths);
        }

        public Map<String, Long> getRecovered() {
            return castDate(recovered);
        }

        /**
         * 格式化日期，M/d/yy -> yyyy/MM/dd
         * @param map
         * @return
         */
        private Map<String, Long> castDate(Map<String, Long> map) {
            Map<String, Long> result = new HashMap<>();
            map.forEach((date,value) ->{
                String format = DateUtil.format(date, "M/d/yy", "yyyy-MM-dd");
                result.put(format,value);
            });
            return result;
        }
    }

    /**
     * 本实体转为List<Case>
     * @param countryId
     * @param slug
     * @return
     */
    public List<Case> cast2List(long countryId, String slug) {
        List<Case> cases = new ArrayList<>();
        timeLine.getCases().forEach((date,totalNum) ->{
            Case aCase = new Case();
            Long deathNum = timeLine.getDeaths().get(date);
            Long recoverNum = timeLine.getRecovered().get(date);
            aCase.setCountryId(countryId);
            aCase.setSlug(slug);
            aCase.setDate(DateUtil.stringToDate(date));
            aCase.setTotalCase(totalNum);
            aCase.setTotalDeath(deathNum);
            aCase.setTotalRecovered(recoverNum);
            cases.add(aCase);
        });
        return cases;
    }

}
