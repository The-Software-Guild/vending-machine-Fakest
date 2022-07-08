package com.ms.sevicelayer;

import com.ms.dao.InsufficientFundsException;
import com.ms.dao.ItemEmptyException;
import com.ms.dao.VendingMachineDao;
import com.ms.dao.VendingMachinePersistenceException;
import com.ms.dto.Change;
import com.ms.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class VendingMachineServiceLayerImpl implements ServiceLayer{

    private final VendingMachineDao vendingMachineDao;

    private BigDecimal wallet;

    public VendingMachineServiceLayerImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
        wallet = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<Item> viewItems() throws VendingMachinePersistenceException {
        return vendingMachineDao.getItems().stream()
                .filter(item -> item.getQuantity() > 0)
                .sorted(Comparator.comparing((i -> Integer.parseInt(i.getITEM_ID()))))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Item selectItem(String itemID) throws InsufficientFundsException, VendingMachinePersistenceException, ItemEmptyException {
        Item item = vendingMachineDao.getItem(itemID);
        if(item.getQuantity() == 0){
            throw new ItemEmptyException("No more of this Item is in stock");
        }
        if(item.getPrice().compareTo(wallet) > 0){
            throw new InsufficientFundsException("Not enough money in your wallet");
        }
        item.setQuantity(item.getQuantity()-1);
        vendingMachineDao.updateItem(item);
        wallet = wallet.subtract(item.getPrice());
        return item;
    }


    @Override
    public void updateItem() throws VendingMachinePersistenceException {

    }

    @Override
    public BigDecimal getSessionWallet() {
        return wallet;
    }

    @Override
    public BigDecimal addToWallet(BigDecimal value) {
        return wallet = wallet.add(value);
    }

    @Override
    public String getChange() {
        String change = Change.getChange(wallet);
        return change;
    }
}
