package org.jpos.qtest;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.util.Log;

import java.io.IOException;


public class EchoHandler extends Log implements ISORequestListener {

    private final static String  MTI_0800 = "0800";

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        try {
            if (m.getMTI().equals(MTI_0800)) {
                m.setResponseMTI();
                m.set(39, "00");
                source.send(m);
            }

        } catch (ISOException  | IOException e) {
            warn("echo-handler", e);
        }
        return true;
    }


}
