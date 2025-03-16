package com.cms.tools.lib.info;

import java.util.HashMap;
import java.util.Map;

import com.cms.tools.lib.pool.ThreadPool;
 
public class PointInf{
   private Map<PointDetail, ThreadPool> mapProcessPool = new HashMap<PointDetail, ThreadPool>();
   private Map<PointDetail, Integer> mapThread = new HashMap<PointDetail, Integer>();
   private Map<PointDetail, String> mapClassName = new HashMap<PointDetail, String>();
   
   public static Point getPoint(PointDetail dPoint) {
     if (dPoint == PointDetail.ASockR || dPoint == PointDetail.ASockW) return Point.A; 
     if (dPoint == PointDetail.BSockR || dPoint == PointDetail.BSockW) return Point.B; 
     if (dPoint == PointDetail.TFP) return Point.TFP; 
     if (dPoint == PointDetail.DelayAB) return Point.Delay; 
     if (dPoint == PointDetail.DelayBA) return Point.Delay; 
     if (dPoint == PointDetail.XGwAB) return Point.XGw; 
     if (dPoint == PointDetail.XGwBA) return Point.XGw; 
     return Point.Input;
   }
   public void putPool(PointDetail dPoint, ThreadPool pool) {
     this.mapProcessPool.put(dPoint, pool);
   }
   public ThreadPool getPool(PointDetail dPoint) {
     return this.mapProcessPool.get(dPoint);
   }
   public void putThreadId(PointDetail dPoint, int nThreadId) {
     this.mapThread.put(dPoint, Integer.valueOf(nThreadId));
   }
   public int getThreadId(PointDetail dPoint) {
     Integer nThreadId = this.mapThread.get(dPoint);
     if (nThreadId == null) {
       return -1;
     }
     return nThreadId.intValue();
   }
   public void putClassName(PointDetail dPoint, String str) {
     this.mapClassName.put(dPoint, str);
   }
   public String getClassName(PointDetail dPoint, String str) {
     return this.mapClassName.get(dPoint);
   }
 }