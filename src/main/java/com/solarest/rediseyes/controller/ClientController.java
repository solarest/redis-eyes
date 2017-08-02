package com.solarest.rediseyes.controller;

import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.RedisClientContainer;
import com.solarest.rediseyes.client.SingletonContainer;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

/**
 * Created by JinJian on 17-5-9.
 * client controller
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String login(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port
    ) {
        RedisClient client = new RedisClient(host, port, null);
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.addRedisClient(client);
        return container.reportContainStatus().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/logoff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String logoff(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port
    ) {
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.removeClient(host, port);
        return container.reportContainStatus().toString();
    }
}
