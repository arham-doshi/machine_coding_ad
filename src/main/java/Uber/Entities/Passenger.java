package Uber.Entities;

public class Passenger extends User {
    Double rating;
    Integer ridesCompleted;

    @Override
    public String toString() {
        return "Passenger{" +
                "rating=" + rating +
                ", ridesCompleted=" + ridesCompleted +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public Passenger(String userid, String name, String email, String number, Double rating, Integer ridesCompleted) {
        super(userid, name, email, number);
        this.rating = rating;
        this.ridesCompleted = ridesCompleted;
    }
    public Passenger(String userid, String name, String email, String number) {
        super(userid, name, email, number);
        this.rating = (double) 0;
        this.ridesCompleted = 0;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRidesCompleted() {
        return ridesCompleted;
    }

    public void setRidesCompleted(Integer ridesCompleted) {
        this.ridesCompleted = ridesCompleted;
    }
}
