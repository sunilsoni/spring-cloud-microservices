package com.jci.portal.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jci.portal.models.Po;

import java.util.List;

@FeignClient("po-service")
public interface PoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    List<Po> findById(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody Po user);
    
    
    

}