package world.ucode.models;

public class Search {
    private String title = "";
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

    public double getStartPrice() {
        if (startPrice.equals("")) {
            return 0;
        }
        return Double.parseDouble(startPrice);
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public int getDuration() {
        if (duration.equals("")) {
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
