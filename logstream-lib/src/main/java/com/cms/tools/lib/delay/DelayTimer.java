package com.cms.tools.lib.delay;

import java.nio.ByteBuffer;

import org.apache.logging.log4j.Logger;

import com.cms.tools.lib.info.Status;
import com.cms.tools.lib.pool.ThreadPool;
import com.cms.tools.lib.services.ServiceItem;


public class DelayTimer extends ThreadPool {
    DelayPool poolW;
    Long nSleep;
    Logger log;
    ServiceItem item;
    Status status;
    public DelayTimer() {
    }

    public DelayTimer(ServiceItem item, Long nSleep, Logger log){
        this.item = item;
        this.nSleep = nSleep;
        this.log = log;
    }

    public void run() {
        try {
            sleep(nSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   

        if(status == Status.Discard){
            log.info(getName() + " message discard");
            item.setBuf(ByteBuffer.allocate(0));
        }else if(status ==  Status.Close){
            log.info(getName() + " message drop");
            item.setBuf(null);
        } else {
            log.info(getName() + " message continue");
        }
        interupsi(item);
    }


}
