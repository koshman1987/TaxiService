package entity;

import java.util.concurrent.Semaphore;

public class Customer implements Runnable{
    private int id;
    private int xPosition;
    private Semaphore semaphore;

    public Customer(int id, Semaphore semaphore){
        this.id = id;
        this.semaphore = semaphore;
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

    public void callTaxi(){
        System.out.println("Client with ID " + getId() + " calls taxi!");

        for (Car car : Company.getCompany().getCarPool()) {
            if (car.isFree().get()) {
                car.occupyCar();
                System.out.println("Car with ID " + car.getId() + " is taken by client with ID " + getId());
                System.out.println("Client with ID " + getId() + " rides on car with ID " + car.getId());
                car.releaseCar();
                return;
            }
        }
    }

    @Override
    public void run(){

        try {
            semaphore.acquire();
            callTaxi();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        semaphore.release();
    }

    @Override
    public String toString(){
        return "Client with ID " + getId();
    }
}