package com.cms.tools.lib.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cms.tools.lib.info.PointDetail;
import com.cms.tools.lib.pool.ThreadPool;

public abstract class serviceRole extends Thread {
    private static final Logger log = LogManager.getLogger(serviceRole.class);
    protected ThreadPool poolW = null;
    protected ThreadPool poolR = null;  
    protected PointDetail dPointMe = null;
    protected ser
    
}
