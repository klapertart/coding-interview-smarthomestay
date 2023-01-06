package coding.interview.smarthomestay.message;

import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

public class ResponseMessageSuccess<T> extends ResponseMessageGeneric {
    private List<T> data;

    public ResponseMessageSuccess(int status, String message, List<T> data) {
        super(status, message);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setStatus(int status) {
        super.setStatus(status);
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected boolean canEqual(Object other) {
        return super.canEqual(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
