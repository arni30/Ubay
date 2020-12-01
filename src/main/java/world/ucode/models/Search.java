package world.ucode.models;

public class Search {
    private String title = "";
//    private float startPrice = 0;
//    private int duration = 0;
    private String startPrice = "";
    private String duration = "";
    private String startTime = "";
    private String description = "";


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getStartPrice() {
        if (startPrice == "") {
            return 0;
        }
        return Float.parseFloat(startPrice);
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public int getDuration() {
        if (duration == "") {
            return 0;
        }
        return Integer.parseInt(duration);
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
