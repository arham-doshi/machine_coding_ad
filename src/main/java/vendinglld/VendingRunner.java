package vendinglld;

public class VendingRunner extends Thread{
    @Override
    public void run() {
        ProductService productService = new ProductService();
        productService.addProduct(1, "coke", 40, 2);
        productService.addProduct(2, "sprite", 50,1);
        VendingMachine vendingMachine = new VendingMachine(productService);
        vendingMachine.addMoney(50);
        vendingMachine.selectProduct(1);
        vendingMachine.checkoutProduct();
        System.out.println("remaining money = : "+vendingMachine.getAvailableMoney());
        vendingMachine.addMoney(40);
        vendingMachine.selectProduct(2);
        vendingMachine.checkoutProduct();
        System.out.println("remaining money = : "+vendingMachine.getAvailableMoney());
        // now testing non ideal states
        try {
            vendingMachine.addMoney(40);
            vendingMachine.checkoutProduct();
            // checking out without selecing product
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("remaining money = : "+vendingMachine.getAvailableMoney());
        // now testing no inventory
        try {
            vendingMachine.selectProduct(2);
            vendingMachine.checkoutProduct();
            // checking out without selecing product
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // now testing illegal product id
        try {
            vendingMachine.selectProduct(3);
            vendingMachine.checkoutProduct();
            // checking out without selecing product
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // now testing checkout from idle
        vendingMachine.withdrawMoney();
        try {

            vendingMachine.checkoutProduct();
            // checking out without selecing product
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // checking insufficient money to by
        try {
            vendingMachine.addMoney(30);
            System.out.println("remaining money = : "+vendingMachine.getAvailableMoney());
            vendingMachine.selectProduct(1);
            vendingMachine.checkoutProduct();
            // checking out without selecing product
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
