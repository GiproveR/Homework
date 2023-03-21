public class Device {
    
    private int nomer;
    private Storage Songs;
    private boolean play;
    
    Device() {//create device with default settings
        this.nomer = 0;
        this.Songs = null;
        this.play = false;
    }
    
    public void getInfo() {//displays information about the currently playing song
        if (this.play & this.Songs!=null) {
            System.out.printf("%s: Now playing: %s\n",this.getClass().getCanonicalName(),this.Songs.getInfo(this.nomer));
        }
        else {
            System.out.printf("The device is not or can't playing music for now.\n");
        }
    }
    
    public void setNomer(int Nomer) throws ArrayIndexOutOfBoundsException{//set the song's number
        if (this.Songs!=null & Nomer/Songs.getLength()<1 & Nomer>=0) {
            this.nomer = Nomer;
            if (this.play) {
                this.getInfo();
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Please, enter another song's number or insert the storage.\n");
        }
    }
    
    public void getList() {//displays information about all songs
        if (this.Songs!=null) {
            for (int i=0;i<this.Songs.getLength();i++) {
                System.out.printf("%d. %s\n",i+1,this.Songs.getInfo(i));
            }
        }
        else {
            System.out.print("Please, insert storage in the device.\n");
        }
    }
    
    public void Play() {//start play music and get information about song
        if (this.Songs!=null) {
            this.play=true;
            this.getInfo();
        }
        else {
            System.out.print("Please, insert storage in the device.\n");
        }
    }
    
    public void NoPlay() {//stop play music
        this.play=false;
    }
    
    public boolean compatible(Storage NewSongs) {//checks, that this device can use the storage
        if (NewSongs instanceof Storage) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void insertStorage(Storage NewSongs) {//connecting device and storage
        if (this.Songs!=null) {
            System.out.print("Please disable the storage in use first.\n");
        }
        else {
            this.Songs = NewSongs;
        }
    }
    
    public void disconnectStorage() {//disconnet storage from device
        this.Songs = null;
    }
}
