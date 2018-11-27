package jackson.bean;

import java.util.*;

/**
 * Country
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 14:24
 */
public class Country {

    private String country_id;
    private Date birthDate;
    private List<String> nation = new ArrayList<String>();
    private String[] lakes;
    private List<Province> provinces = new ArrayList<Province>();
    private Map<String, Integer>  traffic = new HashMap<String, Integer>();

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getNation() {
        return nation;
    }

    public void setNation(List<String> nation) {
        this.nation = nation;
    }

    public String[] getLakes() {
        return lakes;
    }

    public void setLakes(String[] lakes) {
        this.lakes = lakes;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public Map<String, Integer> getTraffic() {
        return traffic;
    }

    public void addTraffic(String key, Integer val) {
        traffic.put(key, val);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("country_id='").append(country_id).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", nation=").append(nation);
        sb.append(", lakes=").append(Arrays.toString(lakes));
        sb.append(", provinces=").append(provinces);
        sb.append(", traffic=").append(traffic);
        sb.append('}');
        return sb.toString();
    }
}
