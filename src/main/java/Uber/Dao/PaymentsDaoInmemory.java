package Uber.Dao;

import Uber.database.PaymentsDBInmemory;

public class PaymentsDaoInmemory implements PaymentsDao{
    PaymentsDBInmemory paymentsDBInmemory;

    public PaymentsDaoInmemory(PaymentsDBInmemory paymentsDBInmemory) {
        this.paymentsDBInmemory = paymentsDBInmemory;
    }

    @Override
    public void addRiderPayment(String riderId, Integer amount) {
        if(this.paymentsDBInmemory.getPendingPayments().get(riderId) == null){
            this.paymentsDBInmemory.getPendingPayments().put(riderId, 0);
        }
        this.paymentsDBInmemory.getPendingPayments().put(riderId, (this.paymentsDBInmemory.getPendingPayments().get(riderId) + amount));
    }

    @Override
    public Integer getRiderPayment(String riderId) {
        // Incase of rider not present In DB we will show 0, but when non existent rider cases out we will throw error.
        return this.paymentsDBInmemory.getPendingPayments().getOrDefault(riderId, 0);
    }

    @Override
    public Integer cashoutPayment(String riderId) {
        Integer payment = this.paymentsDBInmemory.getPendingPayments().get(riderId);
        if(payment == null){
            throw new IllegalArgumentException("cannon cash out for non existent rider");
        }
        if(payment != 0){
            this.paymentsDBInmemory.getPendingPayments().put(riderId, 0);
        }
        System.out.println("for rider " + riderId + " withdrawing payment "+payment);
        return payment;
    }


}
