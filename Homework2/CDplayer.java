public class CDplayer extends Device {
    
    @Override
    public boolean compatible(Storage NewSongs) {//checks, that this CD playye can use the storage
        if (NewSongs instanceof CD) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void insertStorage(Storage NewSongs) {//connecting device and storage
        if (this.compatible(NewSongs)) {
            super.insertStorage(NewSongs);
        }
        else {
            System.out.print("This storage cannot be connected to this playback device.\n");
        }
    }
}
