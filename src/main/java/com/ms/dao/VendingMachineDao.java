package com.ms.dao;

import com.ms.dto.Item;

import java.util.List;

public interface VendingMachineDao {
    List<Item> getItems() throws VendingMachinePersistenceException;

    Item getItem(String itemId) throws ItemEmptyException, VendingMachinePersistenceException;

    Item addItem(Item item) throws ItemEmptyException, VendingMachinePersistenceException;

    void updateItem(Item editedItem) throws ItemEmptyException, VendingMachinePersistenceException;
}
