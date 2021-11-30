package edu.kit.informatik.model.bookingsystem.groupmanagement;

import edu.kit.informatik.model.bookingsystem.coronainformation.CoronaInformation;
import edu.kit.informatik.model.bookingsystem.coronainformation.CoronaTest;
import edu.kit.informatik.model.bookingsystem.coronainformation.LongTermProtection;
import edu.kit.informatik.model.bookingsystem.utils.Date;

public class Person {
    private static final String SPLITTER = ",";
    private static final String SPACE = " ";
    private static final String CORONA_COUNTER = "[%s]";
    private final int id;
    private final String surName;
    private final String lastName;
    private final Role role;
    private final CoronaInformation coronaInformation;

    private int coronaCounter;


    public Person(int id, String surName, String lastName, Role role) {
        this.id = id;
        this.surName = surName;
        this.lastName = lastName;
        this.role = role;
        this.coronaInformation = new CoronaInformation();
        this.coronaCounter++;
    }


    public void updateVaccinationStatus(Date date, LongTermProtection longTermProtection) {
        coronaInformation.updateVaccinationStatus(date, longTermProtection);
    }

    public void addTest(CoronaTest coronaTest) {
        coronaInformation.addTest(coronaTest);
    }

    public Role getRole() {
        return role;
    }

    public boolean isTwoG(Date date) {
        return coronaInformation.isTwoG(date);
    }

    public boolean isThreeG(Date date) {
        return coronaInformation.isThreeG(date);
    }

    public void clearCoronaCounter() {
        this.coronaCounter = 0;
    }

    public int getCoronaCounter() {
        return coronaCounter;
    }

    public void increaseCounter() {
        this.coronaCounter++;
    }

    public String toString(Date date) {
        return id + SPLITTER + SPACE + surName + SPACE + lastName
                + SPLITTER + SPACE + role.toString() + SPLITTER + SPACE + coronaInformation.toString(date);
    }

    public String toStringReportCase(Date date) {
        return toString(date) + SPACE + String.format(CORONA_COUNTER, coronaCounter);
    }

    public enum Role {
        STUDENT("student"),
        LECTURER("lecturer"),
        SECURITY("security");


        private final String roleRepresentation;
        Role(String roleRepresentation) {
            this.roleRepresentation = roleRepresentation;
        }

        public static Role findRoleThroughString(String roleAsString) {
            for (Role role : Role.values()) {
                if (role.roleRepresentation.equals(roleAsString)) {
                    return role;
                }
            }
            return null;
        }




        @Override
        public String toString() {
            return roleRepresentation;
        }
    }
}
