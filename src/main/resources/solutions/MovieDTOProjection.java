public class MovieDTOProjection {

    String title;
    String released;
    String poster;

    Long castSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Long getCastSize() {
        return castSize;
    }

    public void setCastSize(Long castSize) {
        this.castSize = castSize;
    }
}