package Uber.database;

import java.util.HashMap;
import java.util.Map;

public class PaymentsDBInmemory {
    Map<String , Integer> pendingPayments;

    public PaymentsDBInmemory() {
        this.pendingPayments = new HashMap<>();
    }

    public Map<String, Integer> getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(Map<String, Integer> pendingPayments) {
        this.pendingPayments = pendingPayments;
    }
}
