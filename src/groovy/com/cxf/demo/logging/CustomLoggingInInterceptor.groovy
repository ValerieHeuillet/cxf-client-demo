package com.cxf.demo.logging

import org.apache.cxf.common.injection.NoJSR250Annotations
import org.apache.cxf.interceptor.AbstractLoggingInterceptor
import org.apache.cxf.interceptor.Fault
import org.apache.cxf.interceptor.LoggingInInterceptor
import org.apache.cxf.message.Message
import org.apache.cxf.phase.Phase
import cxf.client.demo.simple.SimpleServicePortType
import org.codehaus.groovy.grails.commons.ApplicationHolder

/**
 *
 */
@NoJSR250Annotations
public class CustomLoggingInInterceptor extends AbstractLoggingInterceptor {

    def name
    //SimpleServicePortType simpleServiceClient

    public CustomLoggingInInterceptor() {
        super(Phase.RECEIVE);
        log "Creating the custom interceptor bean"
    }

    public void handleMessage(Message message) throws Fault {
        //get another web service bean here by name and call it
        SimpleServicePortType simpleServiceClient = ApplicationHolder.application.mainContext.getBean("simpleServiceClient")
        log "status is " + simpleServiceClient.simpleMethod1(new cxf.client.demo.simple.SimpleRequest(age: 30, name: 'Test')).status
        log "$name :: I AM IN CUSTOM IN LOGGER!!!!!!!"
    }
}