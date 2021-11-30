package edu.kit.informatik.model.bookingsystem.coronainformation;

import edu.kit.informatik.model.bookingsystem.utils.Date;

public class CoronaTest {
    private final Date date;


    public CoronaTest(Date date) {
        this.date = date;
    }

    public int getIntRepresentationOfDate() {
        return date.getDate();
    }

}
