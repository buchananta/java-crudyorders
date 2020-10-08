package com.lambda.orders.services;

import com.lambda.orders.models.Agent;

public interface AgentServices
{
    Agent save(Agent agent);
    Agent findAgentByAgentcode(long agentcode);
}
