package org.open.covid19.service;

import org.open.covid19.entity.apify.AmericanCase;

import java.util.List;

public interface IApifyService {
    /**
     * 插入美国所有州的数据
     */
    List<AmericanCase> insertAllAmericanStatesCase();
}
