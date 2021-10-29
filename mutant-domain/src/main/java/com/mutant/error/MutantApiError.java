package com.mutant.error;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
public enum MutantApiError {
    MUTANT_API_ERROR_NULL_DNA(400, "Human DNA can't be null"),
    MUTANT_API_GENERAL_PARAM_ERROR(400, "Wrong body params"),
    MUTANT_API_NULL_POINT(500, "Error calculating Mutant"),
    MUTANT_API_ERROR_HERROR_GENERATING_STATS(500, "Error generating Stats"),
    MUTANT_API_ERROR_NO_HUMAN_TO_GENERATE_RATIO(500, "No human to genrate stats"),
    MUTANT_API_ERROR_IS_NOT_MUTANT(403,"Is not mutant!");
    private final int code;
    private final String message;

    MutantApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

