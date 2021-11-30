package edu.kit.informatik.model.bookingsystem.coronainformation;

import edu.kit.informatik.model.bookingsystem.utils.Date;

public class CoronaInformation {
    private static final String NOT_VACCINATED = "no certificate";
    private static final String TESTED = "tested";
    private static final int AMOUNT_OF_DAYS_IN_A_YEAR = 365;
    private final boolean[] hasTest;
    private VaccinationSummary vaccinationSummary;

    public CoronaInformation() {
        //Test array könnte auch ok sein, braucht aber deutlich mehr speicher.
        hasTest = new boolean[AMOUNT_OF_DAYS_IN_A_YEAR];
        this.vaccinationSummary = null;
    }

    public void updateVaccinationStatus(Date date, LongTermProtection longTermProtection) {
        if (this.vaccinationSummary == null) this.vaccinationSummary = new VaccinationSummary(date, longTermProtection);
        vaccinationSummary.updateVaccinationStatus(date, longTermProtection);
    }

    public void addTest(CoronaTest coronaTest) {
        this.hasTest[coronaTest.getIntRepresentationOfDate()] = true;
    }

    public boolean isThreeG(Date date) {
        if (isTwoG(date)) return true;
        return hasTest[date.getDate()];

    }

    public boolean isTwoG(Date date) {
        if (vaccinationSummary == null) return false;
        return vaccinationSummary.hasTwoG(date);

    }


    public String toString(Date date) {
        if (isTwoG(date)) return vaccinationSummary.toString();
        if (isThreeG(date)) return TESTED;
        return NOT_VACCINATED;
    }
}
