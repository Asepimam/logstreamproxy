package com.cms.tools.lib.pool;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cms.tools.lib.info.Point;
import com.cms.tools.lib.info.PointDetail;
import com.cms.tools.lib.info.PointInf;

public class ThreadPool extends Thread {
    private static final Logger log = LogManager.getLogger(ThreadPool.class);
    private Queue<Object> listObj = new LinkedList<>();
    private boolean bClosed = false;
    private PointDetail dPoint;

    public ThreadPool() {}

    public ThreadPool(PointDetail dPoint) {
        this.listObj = new LinkedList<>();
        this.dPoint = dPoint;
    }

    public synchronized void interupsi(Object obj) {
        if (!this.bClosed) {
            log.debug("interupsi : offer ");
            this.listObj.offer(obj);
        }
        notify();
    }

    public synchronized Object isInterupsi() {
        Object obj = null;
        if (this.listObj.isEmpty() && !this.bClosed) {
            try {
                log.debug("isInterupsi : wait");
                wait();
                log.debug("isInterupsi : wakeup");
            } catch (InterruptedException ex) {
                log.error("Interrupted: ", ex);
            }
        } else {
            log.debug("isInterupsi : nowait");
        }
        if (!this.listObj.isEmpty()) {
            obj = this.listObj.poll();
            log.debug("isInterupsi : poll message ");
        }
        return obj;
    }

    public synchronized void close() {
        log.debug("close");
        this.bClosed = true;
        log.debug("notify all");
        notifyAll();
    }

    public PointDetail getPointDetail() {
        return this.dPoint;
    }

    public Point getPoint() {
        return PointInf.getPoint(this.dPoint);
    }
}
