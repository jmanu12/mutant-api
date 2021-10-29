package com.mutant.exception;

import com.mutant.error.MutantApiError;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
public class MutantException extends RuntimeException{

    private static final long serialVersionUID = 9012547197498298107L;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MutantException(String message) {
        super( message);
    }

    public MutantException(String message, Exception e) {
        super( message, e);
    }

    public MutantException(MutantApiError mutantApiError) {
        super( mutantApiError.getMessage());
    }

    public MutantException(MutantApiError mutantApiError, Exception e) {
        super( mutantApiError.getMessage(), e);
        this.code = mutantApiError.getCode();
    }
}
