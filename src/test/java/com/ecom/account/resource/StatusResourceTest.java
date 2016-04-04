/*
 * Copyright (c) 2016
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>http://www.apache.org/licenses/LICENSE-2.0<p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ecom.account.resource;

import com.ecom.account.server.TomcatServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jcordones13 on 4/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringAppConfig.class)
public class StatusResourceTest {

    private static TomcatServer tomcat;
    private static Client client;
    private static WebTarget target;
    private static String rootUrl = "account";

    /** */
    private static final int OK_CODE = 200;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        tomcat = new TomcatServer();
        tomcat.start();
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:" + tomcat.getPort() + rootUrl);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        tomcat.stop();
        tomcat = null;
        client.close();
        client = null;
        target = null;
    }

    @Test
    public void pingTest(){
        WebTarget pingTarget = target.path("/status/ping");
        Response res = pingTarget.request(MediaType.TEXT_PLAIN).get();

        Assert.assertEquals(OK_CODE, res.getStatus());
    }

   /* @Configuration
    static class Config {

    }*/
}
