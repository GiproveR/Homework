import java.util.*;

public class Main {
    public static void main(String[] args)  {
        
        String[] SongsForCD = {"Fly Up","Throne","DONT LET ME DOWN"};//Song's names for CD
        String[] AuthorsForCD = {"Hwang Chang Young","Rival, Neoni, Lost Identities","THE CHAINSMOKERS"};//Song's authors for CD
        
        String[] SongsForVinylRecord = {"Point Of No Return","Anthem Of The Lonely","Fallen Angel"};//Song's names for Vinyl Record
        String[] AuthorsForVinylRecord = {"STARSET","Nine Lashes","Three Days Grace"};//Song's authors for Vinyl Record
        
        String[] SongsForFlashDrive = {"Lucky Strike","Warriors","Edge of My Life"};//Song's names for Flash Drive
        String[] AuthorsForFlashDrive = {"Maroon 5","Imagine Dragons","Manafest"};//Song's authors for Flash Drive
        
        Storage[] Storages = new Storage[3];//creation of storages
        Storages[0] = new CD(SongsForCD,AuthorsForCD);
        Storages[1] = new VinylRecord(SongsForVinylRecord,AuthorsForVinylRecord);
        Storages[2] = new FlashDrive(SongsForFlashDrive,AuthorsForFlashDrive);
        
        Device[] Devices = {new CDplayer(),new VinylPlayer(),new UniversalPlayer()};//creation of playback devices
        Devices[0] = new CDplayer();
        Devices[1] = new VinylPlayer();
        Devices[2] = new UniversalPlayer();
        
        int DeviceNumber = -1;
        int StorageNumber = -1;
        int WantToPlay = -1;
        Listening:
        while (true) {
            Scanner in = new Scanner(System.in);
            try {//TRY for to track bad input(to enter a string instead of an integer, for example)
                System.out.print("""
                                 What do you want to do?:
                                 1. Choose a device to play music;
                                 2. Choose a storage;
                                 3. Disconnect the storage;
                                 4. Choose a song;
                                 5. Play/Stop music
                                 6. Exit
                                 """);
                int Action = in.nextInt();//User choose his action
                switch(Action) {

                    case 1://User choose a device to play music
                        System.out.print("""
                                 Please, select a device to play music:
                                 1. CD player
                                 2. Vinyl player
                                 3. Universal player""");
                        do {
                            try {//Track the ArrayIndexOutOfBoundsException
                                System.out.print("\nYour choice: ");
                                DeviceNumber = in.nextInt()-1;//User choose a device to play music
                                Devices[DeviceNumber].NoPlay();//Call the method for track the Exception of IndexOut
                            }
                            catch (ArrayIndexOutOfBoundsException e) {
                                DeviceNumber = -1;
                                System.out.print("Please, make you choice from list");
                            }
                        }
                        while (DeviceNumber == -1);

                    case 2://User choose a storage with music
                        try {//Checks that the device is selected
                            Devices[DeviceNumber].NoPlay();//Calling the NoPlay method allows to check that the device is selected
                            System.out.print("""
                                             Please, select a storage for your device:
                                             1. CD
                                             2. Vinyl record
                                             3. Flash drive""");
                            do {//User choose the storage
                                try {//Checks that the storage is selected
                                    System.out.print("\nYour choice: ");
                                    StorageNumber = in.nextInt() - 1;//User choose the storage
                                    Devices[DeviceNumber].insertStorage(Storages[StorageNumber]);//Connecting storage and device
                                }
                                catch (ArrayIndexOutOfBoundsException e) {
                                    StorageNumber = -1;
                                    System.out.print("Please, make you choice from list");
                                }
                            }
                            while (StorageNumber == -1 || !Devices[DeviceNumber].compatible(Storages[StorageNumber]));
                            Devices[DeviceNumber].insertStorage(Storages[StorageNumber]);
                            System.out.print("Connection successful!\n");
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.print("Sorry, buy you didn't select a device\n");
                        }
                        break;

                    case 3://Disconnect the Storage
                        try {//Checks that the device is selected
                            Devices[DeviceNumber].disconnectStorage();//Disconnect the Storage
                            System.out.print("Disconnection successful!\n"); 
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.print("Sorry, buy you didn't select a device for disconnet storage\n");
                        }
                        break;

                    case 4://User choose a song
                        try {//Track the ArrayIndexOutOfBoundsException from massiv "Devices" (or Checks that the device is selected)
                            Devices[DeviceNumber].NoPlay();//Calling the NoPlay method allows to check that the device is selected
                            System.out.print("Please, choose song:\n");
                            Devices[DeviceNumber].getList();
                            int SongNumber = 0;
                            int repeat = 1;
                            do {
                                try {//Track the ArrayIndexOutOfBoundsException from Songs
                                    System.out.print("Your choice:\n");
                                    SongNumber = in.nextInt()-1;
                                    Devices[DeviceNumber].setNomer(SongNumber);
                                    repeat = -1;
                                }
                                catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.print("Please select songs from list\n");
                                }
                            }
                            while (repeat == 1);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.print("Please select a device and a storage first\n");
                            break;
                        }
                    case 5://User selects play or stop music
                        System.out.print("If you want to play music, input 1.\nIf you doesn't want to play music, input 0\n");
                        WantToPlay = in.nextInt();
                        try {//Track the ArrayIndexOutOfBoundsException
                            if (WantToPlay == 1) {
                                Devices[DeviceNumber].Play();//play music
                            }
                            else if (WantToPlay == 0) {
                                Devices[DeviceNumber].NoPlay();//stop the music
                                System.out.print("Music stop!\n");
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.print("Please select a device and a storage first\n");
                        }
                        break;
                    case 6:
                        break Listening;//exit
                    default:
                        System.out.print("Please, select your action from list.\n");//called if the user has selected a number outside of the range of possible actions
                }
            }
            catch (InputMismatchException e) {
                System.out.print("Please, check your input.\n");
            }
        }    
    }        
}