package com.BudgetPlanner.service;

import com.BudgetPlanner.entity.Client;


public interface ClientService {
    void saveClient(Client client);
    Client findClientById(int id);
}
