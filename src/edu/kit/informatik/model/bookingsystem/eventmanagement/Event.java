package edu.kit.informatik.model.bookingsystem.eventmanagement;

import edu.kit.informatik.model.bookingsystem.groupmanagement.Person;
import edu.kit.informatik.model.bookingsystem.utils.Date;

public class Event {
    private static final String ERROR_SECURITY_ADD = "Could not add security";
    private static final String VALID_OK = "OK";
    private static final int RATIO_OF_SECURITY = 5;
    private static final String BOOKING_ERROR = "Could not book spot";
    private static final String VALID_BOOK_SPOT = "%s spot(s) left";
    private static final int TWO_WEEKS_IN_DAYS = 14;

    private final Person lecturer;
    private Location location;
    private final int maxCapacity;
    private CoronaRules coronaRules;
    private Date date;
    private Person[] students;
    private Person[] security;
    private int amountOfPeople;
    private int amountOfSecurity;
    private int amountOfStudents;

    public Event(Person lecturer, Location location, int maxCapacity, CoronaRules coronaRules, Date date) {
        this.lecturer = lecturer;
        this.amountOfPeople = 1;
        this.amountOfSecurity = 0;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.coronaRules = coronaRules;
        this.date = date;
        this.students = new Person[maxCapacity];
        this.security = new Person[maxCapacity];
    }

    public String increaseSecurity(Person person) {
        if (amountOfPeople >= maxCapacity || !isAllowedToTakePart(person))
            return ERROR_SECURITY_ADD;
        security[amountOfSecurity] = person;
        amountOfSecurity++;
        amountOfPeople++;
        return VALID_OK;

    }

    public void markCorona(Date date, Person person) {
        if (Math.abs(date.getDate() - this.date.getDate()) > TWO_WEEKS_IN_DAYS) return;

        if (!visitsEvent(person)) return;

        lecturer.increaseCounter();
        for (Person security1: security) {
            security1.increaseCounter();
        }
        for (Person student: students) {
            student.increaseCounter();
        }

    }

    private boolean visitsEvent(Person person) {
        if (person.equals(lecturer)) return true;
        for (Person security1: security) {
            if (person.equals(security1)) return true;
        }

        for (Person student: students) {
            if (person.equals(student)) return true;
        }
        return false;

    }


    private boolean isAllowedToTakePart(Person person) {
        if (coronaRules == CoronaRules.TWO_G)
            return person.isTwoG(date);
        return person.isThreeG(date);
    }

    public String bookSpot(Person person) {
        if (amountOfPeople >= maxCapacity || !isAllowedToTakePart(person)
                || amountOfSecurity * RATIO_OF_SECURITY < amountOfStudents) {
            return BOOKING_ERROR;
        }
        this.students[amountOfStudents] = person;
        amountOfStudents++;
        amountOfPeople++;
        return String.format(VALID_BOOK_SPOT, amountOfSecurity * RATIO_OF_SECURITY - amountOfStudents);

    }
}
