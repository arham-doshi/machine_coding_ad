package vendinglld.entity;

public class StateContext {
    Integer money;
    Integer proudctId;


    public StateContext(Integer money) {
        this.money = money;
        this.proudctId = null;
    }

    public Integer getMoney() {
        return money;
    }
    public Integer addMoney(Integer insertedMoney) {
        if(money == null)money = 0;
        money += insertedMoney;
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getProudctId() {
        return proudctId;
    }

    public void setProudctId(Integer proudctId) {
        this.proudctId = proudctId;
    }
    public void unselectProduct() {
        this.proudctId = null;
    }
}
