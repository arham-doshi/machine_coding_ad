package Uber.services;

import Uber.Dao.PaymentsDao;

public class PaymentsService {
    PaymentsDao paymentsDao;

    public PaymentsService(PaymentsDao paymentsDao) {
        this.paymentsDao = paymentsDao;
    }
    public void payRaider(String riderId, Integer payment){
        this.paymentsDao.addRiderPayment(riderId, payment);
    }
    public Integer getPendingPayment(String rider){
        return this.paymentsDao.getRiderPayment(rider);
    }
    public  Integer cashoutPayment(String rider){
        return this.paymentsDao.cashoutPayment(rider);
    }
}
