package jackson.bean;

import java.util.Arrays;

/**
 * Province
 * <p>
 *
 * @author Feathers
 * @date 2018-04-16 14:25
 */
public class Province {
    private String name;
    private int population;
    private String[] city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Province{");
        sb.append("name='").append(name).append('\'');
        sb.append(", population=").append(population);
        sb.append(", city=").append(Arrays.toString(city));
        sb.append('}');
        return sb.toString();
    }
}
