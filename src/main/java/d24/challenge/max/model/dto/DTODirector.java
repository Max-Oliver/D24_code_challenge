package d24.challenge.max.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTODirector {
    private String name;
    private Integer count;

    public DTODirector(String name, Integer val) {
        this.name = name;
        this.count = val;
    }

    public String getName() {
        return name;
    }
    public void setName(String setName) {
        name = setName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int setCount) {
        count = setCount;
    }
}
