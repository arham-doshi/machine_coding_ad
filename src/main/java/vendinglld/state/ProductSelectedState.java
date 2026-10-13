package vendinglld.state;

import vendinglld.VendingMachine;

public class ProductSelectedState implements  VendingState{

    @Override
    public VendingState addMoney(VendingMachine vendingMachine, Integer money) {
        if(money <= 0){
            throw new IllegalArgumentException("Money cannot be 0 or less than 0");
        }
        vendingMachine.getStateContext().addMoney(money);
        System.out.println("Added money = "+money+" vm, current balance : "+vendingMachine.getStateContext().getMoney());

        return this;

    }

    @Override
    public VendingState checkoutProduct(VendingMachine vendingMachine) {
        Integer productId = vendingMachine.getStateContext().getProudctId();
        if(productId == null){

            throw new IllegalArgumentException("Cannot checkout with no product selected");
        }
        Integer remainingMoney = vendingMachine.getProductService().checkoutProduct(productId, vendingMachine.getStateContext().getMoney() );
        vendingMachine.getStateContext().setMoney(remainingMoney);
        System.out.println("Despensing "+vendingMachine.getProductService().getProduct(productId));
        if(remainingMoney == null || remainingMoney == 0){
            return new IdleState();
        }

        return new MoneyAvailableState();
    }

    @Override
    public VendingState selectProduct(VendingMachine vendingMachine, Integer productId) {
        vendingMachine.getStateContext().setProudctId(productId);
        return this;
    }

    @Override
    public VendingState withdrawMoney(VendingMachine vendingMachine) {
        vendingMachine.getStateContext().setProudctId(null);
        System.out.println("Withdrawing money : "+vendingMachine.getStateContext().getMoney() );
        vendingMachine.getStateContext().setMoney(0);
        return new IdleState();
    }
}
