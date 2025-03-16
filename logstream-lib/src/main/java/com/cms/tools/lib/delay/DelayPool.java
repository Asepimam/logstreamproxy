package com.cms.tools.lib.delay;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.Logger;

import com.cms.tools.lib.info.Status;
import com.cms.tools.lib.pool.ThreadPool;
import com.cms.tools.lib.services.ServiceItem;
public class DelayPool {
    private long nDelay = 0L;
   private Logger log = null;
   private Queue<ServiceItem> listItemSvc;
   private Queue<DelayTimer> listTimer;
   private static Status status = Status.Continue;
 
   
   public DelayPool(Logger log) {
     this.log = log;
     this.listItemSvc = new LinkedList<ServiceItem>();
     this.listTimer = new LinkedList<DelayTimer>();
   }
   
   public void setDelay(long nDelayToSrc) {
     this.nDelay = nDelayToSrc;
   }
   public synchronized void offer(ServiceItem item) {
     if (this.nDelay > 0L) {
       System.out.println("[c]ontinue; [d]iscard; dr[o]p == ");
       DelayTimer delayTimer = new DelayTimer(item, this.nDelay, this.log);
       delayTimer.start();
       this.listTimer.offer(delayTimer);
       this.listItemSvc.offer(item);
     } else {
       interupsi(item);
     } 
   }
   
   public synchronized void interupsiAll(Status status) {
     this; DelayPool.status = status;
     while (this.listTimer.size() > 0) {
       DelayTimer dTimer = this.listTimer.poll();
       dTimer.setStatus(status);
       dTimer.interrupt();
     } 
     this; DelayPool.status = Status.Continue;
   }
   public static Status getStatus() {
     return status;
   }
   
   private void interupsi(ServiceItem item) {
     try {
       item.getItemTFP().poll();
       ThreadPool pool = item.getItemTFP().getPool();
       if (pool != null) {
         pool.interupsi(item);
       }
     } catch (NullPointerException ex) {
       this.log.info(ex.getMessage());
     } catch (IndexOutOfBoundsException ex) {
       this.log.info(ex.getMessage());
     } 
   }
}
