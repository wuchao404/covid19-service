package org.open.covid19.service.impl;

import org.open.covid19.entity.Case;
import org.open.covid19.service.IAnalysisCasesService;

import java.util.List;

public class AnalysisCaseImpl implements IAnalysisCasesService {

    @Override
    public int getCasesSizeByCountry(String iso2) {
        return 0;
    }

    @Override
    public long getTotalCaseByCountryId(int countryId) {
        return 0;
    }

    @Override
    public int insertAllTotalCase(List<Case> cases) {
        return 0;
    }
}
