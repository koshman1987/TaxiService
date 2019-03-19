package entity;

public class Customer {
    private long id;
    private int position;

    public Customer(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Client with ID " + getId();
    }
}