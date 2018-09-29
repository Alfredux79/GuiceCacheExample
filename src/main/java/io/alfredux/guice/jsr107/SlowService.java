package io.alfredux.guice.jsr107;

public interface SlowService {
    String slowCall(String slowCallParam);
    Integer getProcessedCallsCount();
}
