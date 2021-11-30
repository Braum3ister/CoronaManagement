package edu.kit.informatik.model.bookingsystem;

import edu.kit.informatik.model.bookingsystem.coronainformation.CoronaTest;
import edu.kit.informatik.model.bookingsystem.coronainformation.LongTermProtection;
import edu.kit.informatik.model.bookingsystem.eventmanagement.EventManagement;
import edu.kit.informatik.model.bookingsystem.groupmanagement.PersonManagement;
import edu.kit.informatik.model.bookingsystem.groupmanagement.Person;
import edu.kit.informatik.model.bookingsystem.utils.Date;

public class Management {
    private static final String VALID_COMMAND = "OK";
    private Date currentDate;
    private final PersonManagement personManagement;
    private final EventManagement eventManagement;


    public Management() {
        this.currentDate = new Date(0);
        this.personManagement = new PersonManagement();
        this.eventManagement = new EventManagement();

    }


    public String setDate(Date date) {
        this.currentDate = date;
        return VALID_COMMAND;
    }

    public String addPerson(Person.Role role, String surName, String lastName) {

        return String.valueOf(personManagement.addPerson(role, surName, lastName));
    }

    public String addCertificate(int identification, String threeGStatus, Date date) {
        var possibleVaccinationStatusUpdate = LongTermProtection
                .getVaccinationStatusThroughString(threeGStatus);

        if (possibleVaccinationStatusUpdate == null) {
            personManagement.addTest(identification, new CoronaTest(date));
            return VALID_COMMAND;
        }
        personManagement.addCertificate(identification, date, possibleVaccinationStatusUpdate);
        return VALID_COMMAND;
    }

    public String printPerson(int identification) {
        return personManagement.toString(identification, this.currentDate);
    }

    public String toStringOfGroup(Person.Role role) {
        return personManagement.toStringOfGroup(role, currentDate);
    }
    
    public String addEvent(int lecturerId, String location, int maxCapacity, String coronaRule, Date date) {
        return String.valueOf(eventManagement.addEvent(personManagement.getPersonById(lecturerId), location, maxCapacity
                , coronaRule, date));
    }

    public String increaseSecurity(int eventID, int personId) {
        return eventManagement.increaseSecurity(eventID, personManagement.getPersonById(personId));
    }

    public String bookSpot(int eventID, int personID) {
        return eventManagement.bookSpot(eventID, personManagement.getPersonById(personID));
    }

    public String reportCase(int personID) {
        //Alle Veranstaltungen aus den letzten 2 Wochen mit der Person
        //jeden darin auf corona marken
        //foreach schleife drüber
        eventManagement.reportCase(this.currentDate, personManagement.getPersonById(personID));
        return personManagement.reportCase(this.currentDate);
    }

}
