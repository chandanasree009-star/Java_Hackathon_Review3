public class CinemaTicketBooking {
    private String movieName;
    private double ticketPrice;
    private int numberOfTickets;
    private String customerName;

    
    public CinemaTicketBooking(String movieName, double ticketPrice) {
        setMovieDetails(movieName, ticketPrice);
        this.numberOfTickets = 0;
        this.customerName = "";
    }

    
    public CinemaTicketBooking(String movieName, double ticketPrice,
                               String customerName) {
        this(movieName, ticketPrice);
        this.customerName = validateName(customerName);
    }

    public CinemaTicketBooking(String movieName, double ticketPrice,
                               int numberOfTickets, String customerName) {
        this(movieName, ticketPrice, customerName);
        bookTickets(numberOfTickets);
    }

    
    public void bookTickets(int tickets) {
        if (customerName.isEmpty()) {
            throw new IllegalStateException("Enter a customer name first.");
        }
        if (tickets <= 0) {
            throw new IllegalArgumentException("Tickets must be greater than zero.");
        }
        numberOfTickets = tickets;
    }

    
    public void bookTickets(String customer, int tickets) {
        customerName = validateName(customer);
        bookTickets(tickets);
    }

    
    public void bookTickets(String movie, double price, int tickets,
                            String customer) {
        setMovieDetails(movie, price);
        customerName = validateName(customer);
        bookTickets(tickets);
    }

    public boolean matchesCustomer(String name) {
        return customerName.equalsIgnoreCase(name.trim());
    }

    public void displaySummary() {
        double total = ticketPrice * numberOfTickets;
        double discount = numberOfTickets >= 5 ? total * 0.10 : 0;

        System.out.println("\n--- Booking Summary ---");
        System.out.println("Movie: " + movieName.toUpperCase());
        System.out.println("Customer: " + customerName.toUpperCase());
        System.out.println("Tickets: " + numberOfTickets);
        System.out.printf("Ticket price: %.2f%n", ticketPrice);
        System.out.printf("Discount: %.2f%n", discount);
        System.out.printf("Final amount: %.2f%n", total - discount);
    }

    private void setMovieDetails(String movie, double price) {
        if (movie == null || movie.trim().isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Enter a movie name and valid price.");
        }
        movieName = movie.trim();
        ticketPrice = price;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        return name.trim();
    }

    public static void main(String[] args) {
        
        CinemaTicketBooking booking1 =
                new CinemaTicketBooking("Interstellar", 250.00);
        booking1.bookTickets("Asha", 5);
        booking1.displaySummary();

        
        CinemaTicketBooking booking2 =
                new CinemaTicketBooking("Inception", 220.00, "Ravi");
        booking2.bookTickets(3);
        booking2.displaySummary();

        
        CinemaTicketBooking booking3 =
                new CinemaTicketBooking("Dune", 300.00, 2, "Maya");
        booking3.displaySummary();

        
        booking3.bookTickets("Dune Part Two", 320.00, 6, "Maya");
        booking3.displaySummary();

        System.out.println("\nCustomer match: "
                + booking3.matchesCustomer("maya"));
    }
}
