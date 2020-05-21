package org.open.covid19.api.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.jhu.JhuCase;

/**
 * 接口异常捕获
 * @author wuchao
 */
@Slf4j
public class JhuApiFallback implements FallbackFactory<JhuCase> {
    @Override
    public JhuCase create(Throwable cause) {
        log.debug("错误信息：{}",cause.getMessage());
        return new JhuCase();
    }
}
