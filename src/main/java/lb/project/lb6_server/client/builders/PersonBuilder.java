package lb.project.lb6_server.client.builders;

import lb.project.lb6_server.lib.entities.Location;
import lb.project.lb6_server.lib.entities.Person;
import lb.project.lb6_server.lib.ui.UIController;


import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PersonBuilder extends Builder {

    public PersonBuilder(UIController controller) {
        super(controller);
    }

    public PersonBuilder setRequiredFields() {

        setPassportID();
        setHeight();
        setBirthday();
        setLocation();

        return this;

    }

    public Person build() {

        if (isValid())
            return new Person(birthday, height, location, passportID);

        controller.show("Значения " + this + " недопустимы");

        return null;

    }

    @Override
    public String toString() {

        return "Person{" +
                "birthday=" + birthday +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", location=" + location +
                '}';
    }

    private PersonBuilder setBirthday() {

        birthday = null;
        while (birthday == null) {
            controller.show("Значение birthday не должно быть null", false);

            int year = 0;
            while (year < 1900 || year > 2024) {
                controller.show("Год должен принадлежать промежутку [1900; 2024]", false);
                year = controller.readInteger("Введите год рождения: ");
            }

            int month = 0;
            while (month < 1 || month > 12) {
                controller.show("Месяц должен принадлежать промежутку [1; 12]", false);
                month = controller.readInteger("Введите месяц рождения: ");
            }

            int day = 0;
            int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
            while (day < 1 || day > daysInMonth) {
                controller.show("День должен принадлежать промежутку [1; " + daysInMonth + "]", false);
                day = controller.readInteger("Введите день рождения: ");
            }

            birthday = ZonedDateTime.of(year,
                    month,
                    day,
                    0,
                    0,
                    0,
                    0,
                    ZoneId.of("Europe/Moscow"));
        }
        return this;

    }

    private PersonBuilder setHeight() {

        height = null;
        while (height == null) {
            controller.show("Значение height не должно быть null", false);
            height = controller.readDouble("Введите рост: ");
        }
        return this;

    }

    private PersonBuilder setPassportID() {

        passportID = null;
        while (passportID == null || passportID.length() > 46) {
            controller.show("Значение passportID не должно быть null. Длина passportID не должно быть больше 46", false);
            passportID = controller.readString("Введите паспорт: ");
        }
        return this;

    }

    private PersonBuilder setLocation() {

        location = null;
        while (location == null) {
            controller.show("Значение location не должно быть null", false);
            location = new LocationBuilder(controller)
                    .setRequiredFields()
                    .build();
        }
        return this;

    }

    private boolean isValid() {
        return (location != null && passportID != null && height != null && birthday != null);
    }

    private ZonedDateTime birthday;
    private Double height;
    private String passportID;
    private Location location;


}
