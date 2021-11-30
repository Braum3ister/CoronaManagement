package edu.kit.informatik.model.bookingsystem.coronainformation;

import edu.kit.informatik.model.bookingsystem.utils.Date;

public class VaccinationSummary {
    private Date date;
    private LongTermProtection longTermProtection;


    public VaccinationSummary(Date date, LongTermProtection longTermProtection) {
        this.date = date;
        this.longTermProtection = longTermProtection;
    }

    public void updateVaccinationStatus(Date date, LongTermProtection longTermProtection) {
        this.date = date;
        this.longTermProtection = longTermProtection;
    }

    public boolean hasTwoG(Date date) {
        return longTermProtection.isValid(this.date, date);
    }

    @Override
    public String toString() {
        return longTermProtection.toString();
    }
}
