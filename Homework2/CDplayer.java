public class CDplayer extends Device {
    
    @Override
    public boolean compatible(Storage NewSongs) {//проверка, что CD-проигрыватель может использовать хранилище с музыкой
        if (NewSongs instanceof CD) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void insertStorage(Storage NewSongs) {//подсоединение хранилища
        if (this.compatible(NewSongs)) { //проверка на совместимость
            super.insertStorage(NewSongs);
        }
        else { //в случае несовместимости, вывод соответсвующего сообщения
            System.out.print("This storage cannot be connected to this playback device.\n");
        }
    }
}
