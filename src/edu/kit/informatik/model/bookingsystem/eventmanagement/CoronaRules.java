package edu.kit.informatik.model.bookingsystem.eventmanagement;

public enum CoronaRules {
    TWO_G("2G"),
    THREE_G("3G");

    private final String coronaRules;

    CoronaRules(String coronaRules) {
        this.coronaRules = coronaRules;
    }


    public static CoronaRules findCoronaRulesThroughString(String coronaRules) {
        for (CoronaRules coronaRule : CoronaRules.values()) {
            if (coronaRule.coronaRules.equals(coronaRules)) {
                return coronaRule;
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return coronaRules;
    }
}
