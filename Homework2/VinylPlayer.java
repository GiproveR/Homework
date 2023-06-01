public class VinylPlayer extends Device{
    
    @Override
    public boolean compatible(Storage NewSongs) {//проверка, что виниловый проигрыватель может использовать хранилище
        if (NewSongs instanceof VinylRecord) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void insertStorage(Storage NewSongs) {//подсоединение хранилища с музыкой
        if (this.compatible(NewSongs)) {//если совместим, то подключаем
            super.insertStorage(NewSongs);
        }
        else { //в случае несовместимости выводится соответствующее сообщение
            System.out.print("This storage cannot be connected to this playback device.\n");
        }
    }
    
}
