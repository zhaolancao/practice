package com.lancao.practice.ignite.service;

import com.lancao.practice.ignite.config.IgnitePropertiesConfig;
import jakarta.annotation.Resource;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
    @Resource
    private Ignite igniteClient;
    @Resource
    private IgnitePropertiesConfig propertiesConfig;


    public long uniqueId() {
        IgniteAtomicSequence employeeIdSequence = igniteClient.atomicSequence(propertiesConfig.getSequenceName(), propertiesConfig.getIniVal(), true);
        return employeeIdSequence.incrementAndGet();
    }

}
