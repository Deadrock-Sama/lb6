package lb.project.lb6_server.lib.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Comparable<Worker>, Serializable {
    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person) {

        this.name = Objects.requireNonNull(name, "name must not be null");
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates must not be null");
        this.salary = Objects.requireNonNull(salary, "salary must not be null");
        this.position = Objects.requireNonNull(position, "position must not be null");
        this.person = Objects.requireNonNull(person, "person must not be null");

        if (salary.compareTo(0L) < 0)
            throw new IllegalArgumentException("salary must be higher than 0");

        id = ID_GENERATOR.getAndIncrement();
        creationDate = LocalDateTime.now();

    }
    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person, Status status) {
        this(name, coordinates, salary, position, person);
        this.status = status;
    }

    public Worker(String name, Coordinates coordinates, Long salary, Position position, Person person, Status status, int id, LocalDateTime creationDate) {
        this(name, coordinates, salary, position, person, status);
        this.id = id;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Long salary;
    private Position position;
    private Status status;
    private Person person;

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    @Override
    public int compareTo(Worker o) {
        return (int) (salary - o.salary);
    }


    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + (position == null ? "" : position) +
                ", status=" + status +
                ", person=" + person +
                '}';
    }
}