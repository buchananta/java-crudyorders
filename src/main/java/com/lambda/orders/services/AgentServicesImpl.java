package com.lambda.orders.services;

import com.lambda.orders.models.Agent;
import com.lambda.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Transactional
@Service(value = "agentService")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentRepository agentrepos;

    @Transactional
    @Override
    public Agent save(Agent agent)
    {
        return agentrepos.save(agent);
    }
    @Override
    public Agent findAgentByAgentcode(long agentcode)
    {
        Agent rtnAgent = agentrepos.findByAgentcode(agentcode);
        // should have an orelsethrow here, but it won't allow it?
        return rtnAgent;
    }
}
