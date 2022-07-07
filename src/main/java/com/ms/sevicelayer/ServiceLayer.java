package com.ms.sevicelayer;

import com.ms.dao.InsufficientFundsException;
import com.ms.dao.ItemEmptyException;
import com.ms.dao.VendingMachinePersistenceException;
import com.ms.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceLayer {
    List<Item> viewItems() throws ItemEmptyException, VendingMachinePersistenceException;
    Item selectItem(String itemID) throws InsufficientFundsException, VendingMachinePersistenceException, ItemEmptyException;
    void updateItem() throws VendingMachinePersistenceException;
    BigDecimal getSessionWallet();
    BigDecimal addToWallet(BigDecimal value);
}
