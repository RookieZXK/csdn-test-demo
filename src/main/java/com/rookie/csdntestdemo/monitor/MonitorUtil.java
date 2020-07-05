package com.rookie.csdntestdemo.monitor;


import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MonitorUtil {

    @Resource
    private JvmGcMetrics jvmGcMetrics;


    public JvmGcMetrics jvmGcMetrics(){
        return jvmGcMetrics;
    }


}
