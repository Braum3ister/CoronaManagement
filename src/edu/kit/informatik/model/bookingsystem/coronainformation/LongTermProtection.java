package edu.kit.informatik.model.bookingsystem.coronainformation;

import edu.kit.informatik.model.bookingsystem.utils.Date;

public enum LongTermProtection {
    VACCINATED("vaccinated") {
        @Override
        public boolean isValid(Date dateOfVaccinationStatus, Date currentDate) {
            return dateOfVaccinationStatus.dateIsGreaterOrEqual(currentDate);
        }
    },
    RECOVERED("recovered") {
        @Override
        public boolean isValid(Date dateOfVaccinationStatus, Date currentDate) {
            if (!dateOfVaccinationStatus.dateIsGreaterOrEqual(currentDate)) return false;
            return Math.abs(dateOfVaccinationStatus.getDate() - currentDate.getDate()) <= VALID_RECOVERED_STATUS;
        }
    };
    private static final int VALID_RECOVERED_STATUS = 180;

    private final String stringRepresentationOfVaccinationStatus;
    LongTermProtection(String stringRepresentationOfVaccinationStatus) {
        this.stringRepresentationOfVaccinationStatus
                = stringRepresentationOfVaccinationStatus;
    }

    public static LongTermProtection getVaccinationStatusThroughString(String representation) {
        for (LongTermProtection longTermProtection : LongTermProtection.values()) {
            if (longTermProtection.stringRepresentationOfVaccinationStatus.equals(representation)) {
                return longTermProtection;
            }
        }
        return null;
    }

    public abstract boolean isValid(Date dateOfVaccinationStatus, Date currentDate);

    @Override
    public String toString() {
        return stringRepresentationOfVaccinationStatus;
    }
}
