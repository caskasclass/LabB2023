package tmp;

public class Canzone {
    private String trackName;
    private String trackAlbum;


    public Canzone(String name,String album) {
            this.trackName = name;
            this.trackAlbum = album;
            
        }

    
    public Canzone() {
            
        }

    public String getTrackName() {
        return trackName;
    }
    public String getTrackAlbum() {
        return trackAlbum;
    }


}
