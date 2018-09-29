/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.alfredux.guice.jsr107;

import com.google.inject.AbstractModule;

public class CacheExampleModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SlowService.class).to(DefaultSlowService.class);
    }

}
