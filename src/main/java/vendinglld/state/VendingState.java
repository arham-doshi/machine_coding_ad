package vendinglld.state;

import vendinglld.VendingMachine;
import vendinglld.VendingRunner;

public interface VendingState {
    public VendingState addMoney(VendingMachine vendingMachine, Integer money);
    public VendingState checkoutProduct(VendingMachine vendingMachine);
    public VendingState selectProduct(VendingMachine vendingMachine, Integer productId);
    public VendingState withdrawMoney(VendingMachine vendingMachine);
}
