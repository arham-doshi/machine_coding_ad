package vendinglld;

import vendinglld.entity.StateContext;
import vendinglld.state.IdleState;
import vendinglld.state.VendingState;

public class VendingMachine {
    StateContext stateContext;
    ProductService productService;
    VendingState vendingState;
    public VendingMachine(ProductService productService) {
        this.stateContext = new StateContext(0);
        this.productService = productService;
        this.vendingState = new IdleState();
    }

    public StateContext getStateContext() {
        return stateContext;
    }

    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }



    public void addMoney(Integer money) {
        this.vendingState = this.vendingState.addMoney(this, money);
    }


    public void checkoutProduct() {
        // returns product id
        this.vendingState = this.vendingState.checkoutProduct(this);

    }


    public void selectProduct(Integer productId) {
        this.vendingState = this.vendingState.selectProduct(this, productId);
    }


    public void withdrawMoney() {
        this.vendingState = this.vendingState.withdrawMoney(this);

    }
    public Integer getAvailableMoney(){
        Integer money = this.stateContext.getMoney();
        if(money != null)return money;
        return 0;
    }

}
