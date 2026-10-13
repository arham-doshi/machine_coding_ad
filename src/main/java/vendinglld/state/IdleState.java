package vendinglld.state;


import vendinglld.VendingMachine;

public class IdleState implements VendingState{

    @Override
    public VendingState addMoney(VendingMachine vendingMachine, Integer money) {
        if(money <= 0){
            throw new IllegalArgumentException("Money cannot be 0 or less than 0");
        }
        vendingMachine.getStateContext().addMoney(money);
        System.out.println("Added money = "+money+" vm, current balance : "+vendingMachine.getStateContext().getMoney());

        return new MoneyAvailableState();
    }

    @Override
    public VendingState checkoutProduct(VendingMachine vendingMachine) {
        throw new IllegalArgumentException("Cannot checkout product in IDLE State");

    }

    @Override
    public VendingState selectProduct(VendingMachine vendingMachine, Integer productId) {
        throw new IllegalArgumentException("Add money before selecting product");
    }

    @Override
    public VendingState withdrawMoney(VendingMachine vendingMachine) {
        throw new IllegalArgumentException("Cannot withdraw money from IDLE State");
    }
}
