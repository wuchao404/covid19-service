package org.open.covid19.service.impl;

import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Country;
import org.open.covid19.mapper.ResetCountries;
import org.open.covid19.service.ISetCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SetCountriesImpl implements ISetCountries {
    @Autowired
    private ResetCountries resetCountries;
    @Autowired
    private Covid19Api covid19Api;

    @Override
    public boolean setAll() {
        List<Country> countries = covid19Api.getCountries();
        int resultLength = resetCountries.setAll(countries);
        return countries.size() == resultLength;
    }
}
