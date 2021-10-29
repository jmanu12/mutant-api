package com.mutant.error;

import java.util.Objects;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
public class ApiError {
    private Integer code = null;

    private String message = null;

    private String stackTrace = null;

    private String cause = null;

    public ApiError code(Integer code) {
        this.code = code;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ApiError message(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiError stackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public ApiError cause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiError apiError = (ApiError) o;
        return Objects.equals(this.code, apiError.code) &&
                Objects.equals(this.message, apiError.message) &&
                Objects.equals(this.stackTrace, apiError.stackTrace) &&
                Objects.equals(this.cause, apiError.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, stackTrace, cause);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiError {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
        sb.append("    cause: ").append(toIndentedString(cause)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

