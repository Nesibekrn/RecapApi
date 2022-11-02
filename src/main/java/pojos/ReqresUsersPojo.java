package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresUsersPojo {

    private String name;
    private String job;

    public ReqresUsersPojo() {
    }

    public ReqresUsersPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ReqresUsersPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}