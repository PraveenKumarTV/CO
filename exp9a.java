class Screen implements Runnable {
    private String title;
    private String director;
    private String music;
    private String time;
    private double ticketCost;

    public Screen(String title, String director, String music, String time, double ticketCost) {
        this.title = title;
        this.director = director;
        this.music = music;
        this.time = time;
        this.ticketCost = ticketCost;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                displayMovieDetails();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayMovieDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Screen Showing: " + title);
        System.out.println("Director: " + director);
        System.out.println("Music: " + music);
        System.out.println("Show Time: " + time);
        System.out.println("Ticket Cost: $" + ticketCost);
        System.out.println("--------------------------------------------------");
    }
}

public class exp9a {
    public static void main(String[] args) {
        Screen screen1 = new Screen("Avengers: Endgame", "Anthony and Joe Russo", "Alan Silvestri", "12:00 PM", 15.00);
        Screen screen2 = new Screen("The Lion King", "Jon Favreau", "Hans Zimmer", "3:00 PM", 12.00);
        Screen screen3 = new Screen("Joker", "Todd Phillips", "Hildur Guðnadóttir", "6:00 PM", 10.00);
        Screen screen4 = new Screen("Frozen II", "Chris Buck, Jennifer Lee", "Kristen Anderson-Lopez", "9:00 PM", 14.00);
        Screen screen5 = new Screen("Spider-Man: Far From Home", "Jon Watts", "Michael Giacchino", "11:00 AM", 13.00);

        Thread thread1 = new Thread(screen1);
        Thread thread2 = new Thread(screen2);
        Thread thread3 = new Thread(screen3);
        Thread thread4 = new Thread(screen4);
        Thread thread5 = new Thread(screen5);

        thread1.start();
        
        
        
        

        try {
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
            thread4.start();
            thread4.join();
            thread5.start();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
