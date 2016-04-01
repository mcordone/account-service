/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Apr 29, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.ecom.account.bootstrap;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * 
 * @author jcordones13
 *
 */
@ApplicationPath("account")
public class JerseyConfig extends ResourceConfig {
    
    private Logger logger = LoggerFactory.getLogger(JerseyConfig.class);
    
    public JerseyConfig() throws ServletException {
        logger.info("\n***************** JERSEY CONFIGURING ********************\n");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        
        register(RequestContextFilter.class);
        packages("com.aol.advertising.deal");
        register(LoggingFilter.class);
        
        //Adding swagger providers
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        
      //Swagger configuration
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setTitle( "Private Deal API" );
        beanConfig.setDescription("PrivateDeal RESTful API" );
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/account");
        beanConfig.setResourcePackage("com.ecom.account.resource");
        beanConfig.setScan(true);
    }
}


