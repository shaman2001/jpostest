package org.jpos.qtest;

import org.jpos.q2.QBean;

public interface QTestMBean extends QBean {
    void setTickInterval(long tickInterval);
    long getTickInterval();

}
