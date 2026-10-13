package vendinglld.state;

import vendinglld.VendingMachine;
import vendinglld.entity.Product;

public class MoneyAvailableState implements  VendingState
{

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
        throw new IllegalArgumentException("Cannot checkout product without selecting product");

    }

    @Override
    public VendingState selectProduct(VendingMachine vendingMachine, Integer productId) {
        // check for product availaility
        Product product = vendingMachine.getProductService().getProduct(productId);
        vendingMachine.getStateContext().setProudctId(product.getProductId());
        System.out.println("Selecting proudct with id : "+product.getProductId());
        return new ProductSelectedState();
    }

    @Override
    public VendingState withdrawMoney(VendingMachine vendingMachine) {
        vendingMachine.getStateContext().setProudctId(null);
        System.out.println("Withdrawing money : "+vendingMachine.getStateContext().getMoney() );
        vendingMachine.getStateContext().setMoney(0);
        return new IdleState();

    }
}
