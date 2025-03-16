package com.cms.tools.lib.delay;

import com.cms.tools.lib.info.PointDetail;
import com.cms.tools.lib.pool.ThreadPool;

public class Delay {
    public DelayPool poolWDelay;
   
   public Delay(PointDetail dPoint, ThreadPool poolR, DelayPool poolWDelay) {
     this.poolWDelay = poolWDelay;
     init(dPoint, poolR, null, "[" + dPoint.toString() + "]");
   }
 
   
   public boolean process() {
     Status status = Status.UnStatus;
     this; setPriority(5);
     while (true) {
       this.itemR = (ItemSvc)this.poolR.isInterupsi();
       if (this.itemR == null)
         break; 
       if (this.itemR.getBufLength() > 0) {
         this.poolWDelay.offer(this.itemR); continue;
       }  if (this.itemR.getItemTFP() == null);
     } 
 
     
     return true;
   }
}
