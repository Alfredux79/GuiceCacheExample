/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.alfredux.guice.jsr107;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

public class DefaultSlowService implements SlowService {

    private Integer processedCalls = 0;

    @Override
    @CacheResult(cacheName = "slow-call-cache")
    public String slowCall(@CacheKey String slowCallParam) {
        Utils.timeDelay(1000);
        processedCalls++;
        return slowCallParam;
    }

    @Override
    public Integer getProcessedCallsCount() {
        return processedCalls;
    }


}
