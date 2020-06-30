package examples;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"placeName", "longitude", "state", "stateAbbreviation", "latitude"})
public class Place extends BasePage {
    private String placeName;
    private String longitude;
    private String state;
    private String stateAbbreviation;
    private String latitude;

    public Place() {
        this.placeName = "Beverly Hils";
        this.longitude = "-118.4065";
        this.state = "California";
        this.stateAbbreviation = "CA";
        this.latitude = "34.0901";
    }

    @JsonProperty("place name")
    public String getPlaceName(){
        return placeName;
    }

    @JsonProperty("place name")
    public void setPlaceName(String placeName){
        this.placeName = placeName;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    @JsonProperty("state abbreviation")
    public String getStateAbbreviation(){
        return stateAbbreviation;
    }

    @JsonProperty("state abbreviation")
    public void setStateAbbreviation(String stateAbbreviation){
        this.stateAbbreviation = stateAbbreviation;
    }


    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

}
