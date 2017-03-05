package bean;

import controller.command.impl.SignIn;

/**
 * Created by irinaleibutina on 25.02.17.
 */
public class EmployeeHR  extends User{

    private String workId;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    @Override
    public String toString() {

        return super.toString() + "EmployeeHR{" +
                "workId='" + workId + '\'' +
                '}';
    }
}
