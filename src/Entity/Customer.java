package Entity;

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

    public void callTaxi(Car car){
        System.out.println("Client with ID " + getId() + " calls taxi!");

        if(car.isFree().get()) {
            car.occupyCar();
            System.out.println("Client with ID " + getId() + " rides on taxi!");
            car.releaseCar();
        }
    }

    @Override
    public String toString(){
        return "Client with ID " + getId();
    }
}
