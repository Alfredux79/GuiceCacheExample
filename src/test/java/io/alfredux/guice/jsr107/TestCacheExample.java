/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.alfredux.guice.jsr107;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jsr107.ri.annotations.guice.module.CacheAnnotationsModule;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.CacheManager;
import javax.cache.Caching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCacheExample {

    private static Logger log = LoggerFactory.getLogger(TestCacheExample.class);

    private final static Integer CALLS = 10;

    private static SlowService slowService;
    private static Injector injector;
    private static CacheManager cacheManager;

    @BeforeClass
    public static void setUp() {
        injector = Guice.createInjector(new CacheExampleModule(), new CacheAnnotationsModule());
        slowService = injector.getInstance(SlowService.class);
        cacheManager = Caching.getCachingProvider().getCacheManager();
        log.info("Configured caches: "+cacheManager.getCacheNames());
    }

    @Test
    public void runMultipleCallsAndCacheIsUsed() {

        for (int i = 0; i < CALLS; i++) {
            long startTime = System.currentTimeMillis();
            assertEquals(slowService.slowCall("paco"), "paco");
            long duration = System.currentTimeMillis() - startTime;
            log.info(String.format("Service 'slowCall' method executed in  %d ms",duration));
            Utils.timeDelay(1000);
        }

        log.info(String.format("Service 'slowCall' method processed %d of %d calls",
                slowService.getProcessedCallsCount(), CALLS));

        assertNotEquals(CALLS, slowService.getProcessedCallsCount());
    }

}
