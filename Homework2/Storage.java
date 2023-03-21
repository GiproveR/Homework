public class Storage {

    private String[] Song;
    private String[] Author;
    
    Storage(String[] NewSongs,String[] NewAuthors) {//create Storage with aviable Songs
        this.Song = NewSongs;
        this.Author = NewAuthors;
    }
    
    String getInfo(int nomer) {//gives song's name and song's author
        return String.format("%s - %s", this.Song[nomer], this.Author[nomer]);
    }
    
    int getLength() {//gives the number of songs
        return this.Song.length;
    }
}
