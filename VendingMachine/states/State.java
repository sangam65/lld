package VendingMachine.states;

import java.util.List;

import VendingMachine.Coin;
import VendingMachine.VendingMachine;

public interface State {
    void clickOnInsertCoinButton(VendingMachine vendingMachine)throws Exception;
    void insertCoinButton(VendingMachine vendingMachine,List<Coin>coins) throws Exception;

    void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception;
    void chooseProduct(VendingMachine vendingMachine,List<Integer>productCodes) throws Exception;

    List<Coin> refundFullMoney(VendingMachine vendingMachine) throws Exception;
    

}
