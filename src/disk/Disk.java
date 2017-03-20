package disk;

/**
 * Created by Егор on 17.03.17.
 */
public abstract class Disk {
    protected int capacity;
    protected String name;

    public Disk() {
        capacity = 0;
        name = "Disk name";
    }

    public Disk(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk)) return false;

        Disk disk = (Disk) o;

        if (capacity != disk.capacity) return false;
        return name.equals(disk.name);
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + name.hashCode();
        return result;
    }
}
