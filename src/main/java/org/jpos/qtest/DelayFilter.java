package org.jpos.qtest;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.util.LogEvent;

public class DelayFilter implements ISOFilter, Configurable {

    private long delay;

    public DelayFilter() {
        super();
        this.delay = 0L;
    }

    public DelayFilter(long delay) {
        super();
        this.delay = delay;
    }

    @Override
    public ISOMsg filter(ISOChannel channel, ISOMsg m, LogEvent evt) throws VetoException {
        evt.addMessage ("<delay-filter delay=\""+delay+"\"/>");
        if (delay > 0L)
            ISOUtil.sleep(delay);
        return m;
    }

    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {
        delay = cfg.getInt("delay");
    }


}
