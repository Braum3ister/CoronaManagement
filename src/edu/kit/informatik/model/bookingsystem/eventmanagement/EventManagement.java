package edu.kit.informatik.model.bookingsystem.eventmanagement;

import edu.kit.informatik.model.bookingsystem.groupmanagement.Person;
import edu.kit.informatik.model.bookingsystem.utils.Date;

public class EventManagement {
    private static final int MAX_AMOUNT_OF_EVENTS = 100;
    private final Event[] events;
    private int eventID;

    public EventManagement() {
        this.events = new Event[MAX_AMOUNT_OF_EVENTS];
        this.eventID = 0;
    }

    public int addEvent(Person lecturer, String location, int maxCapacity, String coronaRule, Date date) {
        events[eventID] = new Event(lecturer, new Location(location), maxCapacity
                , CoronaRules.findCoronaRulesThroughString(coronaRule), date);
        return eventID++;
    }

    public String increaseSecurity(int eventID, Person person) {
        return events[eventID].increaseSecurity(person);
    }

    public String bookSpot(int eventID, Person person) {
        return events[eventID].bookSpot(person);
    }

    public void reportCase(Date date, Person person) {
        for (Event event: events) {
            event.markCorona(date, person);
        }

    }
}
