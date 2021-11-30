package edu.kit.informatik.model.bookingsystem.groupmanagement;

import edu.kit.informatik.model.bookingsystem.coronainformation.CoronaTest;
import edu.kit.informatik.model.bookingsystem.coronainformation.LongTermProtection;
import edu.kit.informatik.model.bookingsystem.utils.Date;

public class PersonManagement {
    private static final int MAX_AMOUNT_OF_PEOPLE = 100;
    private static final String NO_GROUP_IN_THE_SYSTEM = "No %s in the system";
    private int currentID;
    private final Person[] persons;


    public PersonManagement() {
        this.persons = new Person[MAX_AMOUNT_OF_PEOPLE];
        this.currentID = 0;
    }

    public int addPerson(Person.Role role, String surName, String lastName) {
        this.persons[currentID] = new Person(currentID, surName, lastName, role);
        return currentID++;
    }

    public void addTest(int identification, CoronaTest coronaTest) {
        persons[identification].addTest(coronaTest);
    }

    public void addCertificate(int identification, Date date, LongTermProtection longTermProtection) {
        persons[identification].updateVaccinationStatus(date, longTermProtection);
    }

    public Person getPersonById(int identification) {
        return persons[identification];
    }


    public String toString(int identification, Date date) {
        return persons[identification].toString(date);
    }

    public String toStringOfGroup(Person.Role role, Date date) {
        var output = new StringBuilder();

        for (Person person : persons) {
            if (person.getRole().equals(role)) {
                output.append(person.toString(date)).append(System.lineSeparator());
            }
        }
        if (output.isEmpty()) return String.format(NO_GROUP_IN_THE_SYSTEM, role.toString());
        return output.deleteCharAt(output.length() - 1).toString();
    }

    public String reportCase(Date date) {
        var output = new StringBuilder();
        for (Person person: persons) {
            if (person.getCoronaCounter() != 0) {
                output.append(person.toStringReportCase(date)).append(System.lineSeparator());
                person.clearCoronaCounter();
            }
        }
        return output.deleteCharAt(output.length() - 1).toString();
    }
}
