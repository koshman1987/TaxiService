package entity;

import java.util.List;

public class Customer{
    private int id;
    private int xPosition;

    public Customer(int Id){
        this.id = Id;
    }

    public int getId() {
        return id;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void callTaxi(List<Car> cars){
        System.out.println("Client with ID " + getId() + " calls taxi!");

        for (Car car : cars) {
            if (car.isFree().get()) {
                car.occupyCar();
                System.out.println("Client with ID " + getId() + " rides on taxi!");
                car.releaseCar();
                return;
            }
        }
    }

    @Override
    public String toString(){
        return "Client with ID " + getId();
    }
}
