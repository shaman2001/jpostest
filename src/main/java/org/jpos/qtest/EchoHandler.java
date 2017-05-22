package org.jpos.qtest;

import org.jpos.iso.*;
import org.jpos.util.Log;

import java.io.IOException;
import java.util.Date;


public class EchoHandler extends Log implements ISORequestListener {

    private final static String  MTI_0800 = "0800";

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        try {
            logISOMsg(m);
            if (m.getMTI().equals(MTI_0800)) {
                ISOMsg isoResp = (ISOMsg) m.clone();
                isoResp.setResponseMTI();
                isoResp.set(7, getCurISODateTime());
                isoResp.set(39, "00");
                logISOMsg(isoResp);
                source.send(isoResp);
            }

        } catch (ISOException  | IOException e) {
            warn("echo-handler", e);
        }
        return true;
    }

    private void logISOMsg(ISOMsg msg) throws ISOException {
        info("-----------------------------------");
        if (msg.isRequest()) {
            info("REQUEST MESSAGE");
        } else if (msg.isResponse()) {
            info("RESPONSE MESSAGE");
        }
        info(msg.getMTI());
        int maxField = msg.getMaxField();
        for(int i=1; i<=maxField; i++) {
            if (msg.hasField(i)) {
                info("Field - " + i + ": " + msg.getString(i));
            }
        }
        info("-----------------------------------");

    }

    private String getCurISODateTime() {
        Date date = new Date();
        return ISODate.getDateTime(date);

    }


}
