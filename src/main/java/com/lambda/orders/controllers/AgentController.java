package com.lambda.orders.controllers;

import com.lambda.orders.models.Agent;
import com.lambda.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("agents")
public class AgentController
{
    @Autowired
    AgentServices agentService;

    @GetMapping(value = "agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> findAgentById(@PathVariable long id)
    {
        Agent rtnAgent = agentService.findAgentByAgentcode(id);
        return new ResponseEntity<>(rtnAgent, HttpStatus.OK);
    }
}
