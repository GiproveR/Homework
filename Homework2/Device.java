public class Device {
    
    private int nomer; //номер текущей проигрываемой песни
    private Storage Songs; //подсоединённый носитель с песнями
    private boolean play; //переменная, отвечающая за проигрывание песни
    
    Device() {//создание устройства с настройками по умолчанию
        this.nomer = 0;
        this.Songs = null;
        this.play = false;
    }
        
    public void setNomer(int Nomer) throws ArrayIndexOutOfBoundsException{//выбор песни для воспроизведения
        if (this.Songs!=null & Nomer/Songs.getLength()<1 & Nomer>=0) {//если подключён носитель с песнями и песня с номером существует, песня выбирается
            this.nomer = Nomer;
            if (this.play) { //если включено воспроизведение песен, так же выводится информация о выбранной песне
                this.getInfo();
            }
        }
        else { //иначе пользователя просят выбрать существующую песню или вставить носитель с песнями
            throw new ArrayIndexOutOfBoundsException("Please, enter another song's number or insert the storage.\n");
        }
    }
    
    public void getInfo() {//отображение информации о текущей воспроизводимой песне
        if (this.play & this.Songs!=null) {//выполняется, если музыка воспроизводится и подключено хранилище с песнями
            System.out.printf("%s: Now playing: %s\n",this.getClass().getCanonicalName(),this.Songs.getInfo(this.nomer));
        }
        else { //иначе программа просит вставить носитель музыкальных композиций или включить воспроизведение
            System.out.printf("The device is not or can't playing music for now.\n");
        }
    }
    
    public void getList() {//отображение информации обо всех песнях
        if (this.Songs!=null) {//информация выводится, если подключён носитель с музыкальными композициями
            for (int i=0;i<this.Songs.getLength();i++) {
                System.out.printf("%d. %s\n",i+1,this.Songs.getInfo(i));
            }
        }
        else { //если устройства с песнями нет, выводится соответсвующая ошибка
            System.out.print("Please, insert storage in the device.\n");
        }
    }
    
    public void Play() {//запуск воспроизведения музыки
        if (this.Songs!=null) { //воспроизведение начинается, если подключён носитель с песнями
            this.play=true;
            this.getInfo(); //выводится информация обо всех доступных песнях
        }
        else { //иначе выводится соответствующая ошибка
            System.out.print("Please, insert storage in the device.\n");
        }
    }
    
    public void NoPlay() {//остановка воспроизведения музыки
        this.play=false;
    }
    
    public boolean compatible(Storage NewSongs) {//проверка совместимости воспроизводимого устройства и носителя с песнями
        if (NewSongs instanceof Storage) { //если совместимы, возвращает true
            return true;
        }
        else { //иначе возвращается false
            return false;
        }
    }
    
    public void insertStorage(Storage NewSongs) {//подключение хранилища песен
        if (this.Songs!=null) { //если используется другой носитель, мы не можем подключить новый
            System.out.print("Please disable the storage in use first.\n");
        }
        else { //иначе подключается новое хранилище с песнями
            this.Songs = NewSongs;
        }
    }
    
    public void disconnectStorage() {//остановка воспроизведения музыки и отсоединение подключенного хранилища
        this.play = false; //остановка воспроизведения
        this.Songs = null; //отключение устройства
    }
}
