package Uber.Dao;

public interface PaymentsDao {
    public void addRiderPayment(String riderId, Integer amount);
    public Integer getRiderPayment(String riderId);
    public Integer cashoutPayment(String riderId);
}
