import java.text.NumberFormat;
import java.util.Locale;

// ============================================
// BELAJAR OOP & SOLID PRINCIPLES - LENGKAP
// ============================================

public class BelajarLengkap {
    
    // Utility untuk format Rupiah (Java 24 compatible)
    public static String formatRupiah(double amount) {
        // Locale.of() adalah cara modern untuk Java 19+
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        return formatter.format(amount);
    }
    
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   BELAJAR OOP & SOLID PRINCIPLES");
        System.out.println("==========================================\n");
        
        // Buat object untuk demo
        Employee emp1 = new Employee("Budi", 5000000, "EMP001");
        Manager mgr1 = new Manager("Charlie", 15000000, "MGR001", 5000000, 5);
        Intern int1 = new Intern("Dewi", 5000000, "INT001", 3, "Universitas Indonesia");
        Freelance fr1 = new Freelance("Eka", 7000000, "FRE001", 3000000, 3);
        
        // ============ DEMO OOP ============
        System.out.println("=== OBJECT-ORIENTED PROGRAMMING (OOP) ===\n");
        
        // 1. ENCAPSULATION
        System.out.println("--- 1. Encapsulation ---");
        emp1.displayInfo();
        System.out.println("Mengubah gaji Budi...");
        emp1.setSalary(5500000);
        emp1.displayInfo();
        System.out.println();
        
        // 2. INHERITANCE
        System.out.println("--- 2. Inheritance ---");
        mgr1.displayInfo();
        mgr1.giveTask("Selesaikan laporan keuangan");
        System.out.println();
        
        int1.displayInfo();
        int1.learn();
        System.out.println();
        
        // 3. POLYMORPHISM
        System.out.println("--- 3. Polymorphism ---");
        Employee[] employees = {emp1, mgr1, int1, fr1};
        for (Employee emp : employees) {
            emp.work();  // Method sama, hasil beda
        }
        System.out.println();
        
        // 4. ABSTRACTION
        System.out.println("--- 4. Abstraction ---");
        ITDepartment itDept = new ITDepartment(10, 50);
        HRDepartment hrDept = new HRDepartment(5, 20);
        
        itDept.displayInfo();
        System.out.println("Budget IT: " + formatRupiah(itDept.calculateBudget()));
        hrDept.displayInfo();
        System.out.println("Budget HR: " + formatRupiah(hrDept.calculateBudget()));
        
        // ============ DEMO SOLID ============
        System.out.println("\n=== SOLID PRINCIPLES ===\n");
        
        // S - Single Responsibility Principle
        System.out.println("--- S: Single Responsibility Principle ---");
        EmployeeRepository repo = new EmployeeRepository();
        EmailService emailSvc = new EmailService();
        ReportGenerator reportGen = new ReportGenerator();
        
        EmployeeService empService = new EmployeeService(repo, emailSvc, reportGen);
        empService.registerNewEmployee(emp1);
        
        // O - Open/Closed Principle
        System.out.println("\n--- O: Open/Closed Principle ---");
        
        TaxCalculator[] taxCalculators = {
            new PermanentEmployeeTax(),
            new ContractEmployeeTax(), 
            new InternTax(),
            new FreelanceTax()
        };
        
        String[] labels = {"Pegawai Tetap", "Pegawai Kontrak", "Intern", "Freelance"};
        Employee[] taxEmployees = {emp1, emp1, int1, fr1};
        
        for (int i = 0; i < taxCalculators.length; i++) {
            double tax = taxCalculators[i].calculateTax(taxEmployees[i]);
            System.out.println("Pajak " + labels[i] + ": " + formatRupiah(tax));
            
            // Detail untuk freelance
            if (i == 3 && taxEmployees[i] instanceof Freelance) {
                Freelance fr = (Freelance) taxEmployees[i];
                double projectIncome = fr.getProjectRate() * fr.getCompletedProjects();
                double total = fr.getSalary() + projectIncome;
                System.out.println("   (Detail: Gaji " + formatRupiah(fr.getSalary()) + 
                                 " + Project " + formatRupiah(projectIncome) + 
                                 " = " + formatRupiah(total) + " × 3%)");
            }
        }
        
        // L - Liskov Substitution Principle
        System.out.println("\n--- L: Liskov Substitution Principle ---");
        System.out.println("✅ LSP diimplementasikan dengan Flyable interface");
        System.out.println("   - Bird sebagai abstract class dengan method eat()");
        System.out.println("   - Flyable interface untuk yang bisa terbang");
        System.out.println("   - Sparrow bisa terbang, Penguin tidak");
        
        Sparrow sparrow = new Sparrow();
        PenguinLSP penguin = new PenguinLSP();
        
        sparrow.eat();
        sparrow.fly();
        penguin.eat();
        // penguin.fly(); // Tidak bisa, ini benar!
        
        // I - Interface Segregation Principle
        System.out.println("\n--- I: Interface Segregation Principle ---");
        System.out.println("✅ Interface dipisah sesuai tanggung jawab:");
        System.out.println("   - Workable: untuk yang bekerja");
        System.out.println("   - Eatable: untuk yang makan");
        System.out.println("   - Sleepable: untuk yang tidur");
        
        Robot robot = new Robot();
        HumanWorker human = new HumanWorker();
        
        robot.work();
        human.work();
        human.eat();
        human.sleep();
        
        // D - Dependency Inversion Principle
        System.out.println("\n--- D: Dependency Inversion Principle ---");
        
        // Demo berbagai notification sender
        NotificationSender[] senders = {
            new EmailNotification(),
            new WhatsAppNotification(),
            new TelegramNotification()
        };
        
        String[] messages = {
            "Selamat datang di perusahaan!",
            "Jangan lupa rapat besok jam 10 pagi!",
            "Update project terbaru"
        };
        
        for (int i = 0; i < senders.length; i++) {
            NotificationService notifService = new NotificationService(senders[i]);
            notifService.notifyEmployee(emp1, messages[i]);
        }
        
        // Bonus: Multiple recipients
        System.out.println("\n--- Bonus: Multiple Recipients ---");
        NotificationService waService = new NotificationService(new WhatsAppNotification());
        
        Employee[] team = {
            new Employee("Budi", 5000000, "EMP001"),
            new Employee("Ani", 6000000, "EMP002"),
            new Manager("Charlie", 15000000, "MGR003", 5000000, 5)
        };
        
        waService.notifyEmployee("Meeting tim jam 2 siang!", team);
        
        System.out.println("\n==========================================");
        System.out.println("   PROGRAM SELESAI - SEMUA BERJALAN ✅");
        System.out.println("==========================================");
    }
}

// ============================================
// CLASS-CLASS OOP
// ============================================

// Employee Class (Encapsulation)
class Employee {
    private String name;
    private double salary;
    private String id;
    
    public Employee(String name, double salary, String id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }
    
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getId() { return id; }
    
    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Gaji harus positif!");
        }
    }
    
    public void displayInfo() {
        System.out.println("ID: " + id + ", Nama: " + name + ", Gaji: " + BelajarLengkap.formatRupiah(salary));
    }
    
    public void work() {
        System.out.println("👤 Employee " + name + " sedang bekerja umum");
    }
}

// Manager Class (Inheritance)
class Manager extends Employee {
    private double bonus;
    private int teamSize;
    
    public Manager(String name, double salary, String id, double bonus, int teamSize) {
        super(name, salary, id);
        this.bonus = bonus;
        this.teamSize = teamSize;
    }
    
    public void giveTask(String task) {
        System.out.println("👔 Manager " + getName() + " memberi tugas: " + task);
    }
    
    @Override
    public void work() {
        System.out.println("👔 Manager " + getName() + " sedang memimpin tim");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  (Manager dengan tim " + teamSize + " orang, bonus " + 
                          BelajarLengkap.formatRupiah(bonus) + ")");
    }
}

// Intern Class (Inheritance)
class Intern extends Employee {
    private int durationMonths;
    private String university;
    
    public Intern(String name, double salary, String id, int durationMonths, String university) {
        super(name, salary, id);
        this.durationMonths = durationMonths;
        this.university = university;
    }
    
    public void learn() {
        System.out.println("📚 Intern " + getName() + " sedang belajar");
    }
    
    @Override
    public void work() {
        System.out.println("📚 Intern " + getName() + " sedang belajar dan membantu tugas");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  (Intern dari " + university + ", " + durationMonths + " bulan)");
    }
}

// Freelance Class (Tugas 1)
class Freelance extends Employee {
    private double projectRate;
    private int completedProjects;
    
    public Freelance(String name, double salary, String id, double projectRate, int completedProjects) {
        super(name, salary, id);
        this.projectRate = projectRate;
        this.completedProjects = completedProjects;
    }
    
    public double getProjectRate() { return projectRate; }
    public int getCompletedProjects() { return completedProjects; }
    
    public double calculateProjectIncome() {
        return projectRate * completedProjects;
    }
    
    @Override
    public void work() {
        System.out.println("💻 Freelance " + getName() + " sedang mengerjakan project client");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  (Freelance - Tarif: " + BelajarLengkap.formatRupiah(projectRate) + 
                          "/project, Selesai: " + completedProjects + 
                          " project, Pendapatan project: " + BelajarLengkap.formatRupiah(calculateProjectIncome()) + ")");
    }
}

// Abstract Class Department
abstract class Department {
    protected String name;
    protected int employeeCount;
    
    public Department(String name, int employeeCount) {
        this.name = name;
        this.employeeCount = employeeCount;
    }
    
    public abstract double calculateBudget();
    
    public void displayInfo() {
        System.out.println("Department: " + name + ", " + employeeCount + " pegawai");
    }
}

class ITDepartment extends Department {
    private int serverCount;
    
    public ITDepartment(int employeeCount, int serverCount) {
        super("IT", employeeCount);
        this.serverCount = serverCount;
    }
    
    @Override
    public double calculateBudget() {
        return (employeeCount * 10000000) + (serverCount * 5000000);
    }
}

class HRDepartment extends Department {
    private int recruitmentQuota;
    
    public HRDepartment(int employeeCount, int recruitmentQuota) {
        super("HR", employeeCount);
        this.recruitmentQuota = recruitmentQuota;
    }
    
    @Override
    public double calculateBudget() {
        return (employeeCount * 8000000) + (recruitmentQuota * 2000000);
    }
}

// ============================================
// CLASS-CLASS SOLID
// ============================================

// S - Single Responsibility Principle
class EmployeeRepository {
    public void saveToDatabase(Employee emp) {
        System.out.println("💾 Menyimpan data karyawan " + emp.getName() + " ke database...");
    }
}

class EmailService {
    public void sendWelcomeEmail(Employee emp) {
        System.out.println("📧 Mengirim email selamat datang ke " + emp.getName());
    }
}

class ReportGenerator {
    public void generateReport(Employee emp) {
        System.out.println("📊 Membuat laporan untuk: " + emp.getName());
    }
}

class EmployeeService {
    private EmployeeRepository repository;
    private EmailService emailService;
    private ReportGenerator reportGenerator;
    
    public EmployeeService(EmployeeRepository repository, EmailService emailService, ReportGenerator reportGenerator) {
        this.repository = repository;
        this.emailService = emailService;
        this.reportGenerator = reportGenerator;
    }
    
    public void registerNewEmployee(Employee emp) {
        System.out.println("\n===== Proses Registrasi Pegawai Baru =====");
        repository.saveToDatabase(emp);
        emailService.sendWelcomeEmail(emp);
        reportGenerator.generateReport(emp);
    }
}

// O - Open/Closed Principle
interface TaxCalculator {
    double calculateTax(Employee emp);
}

class PermanentEmployeeTax implements TaxCalculator {
    @Override
    public double calculateTax(Employee emp) {
        return emp.getSalary() * 0.1;  // Pajak 10%
    }
}

class ContractEmployeeTax implements TaxCalculator {
    @Override
    public double calculateTax(Employee emp) {
        return emp.getSalary() * 0.05;  // Pajak 5%
    }
}

class InternTax implements TaxCalculator {
    @Override
    public double calculateTax(Employee emp) {
        return emp.getSalary() * 0.02;  // Pajak 2%
    }
}

class FreelanceTax implements TaxCalculator {  // Tugas 2
    @Override
    public double calculateTax(Employee emp) {
        if (emp instanceof Freelance) {
            Freelance freelance = (Freelance) emp;
            double total = emp.getSalary() + freelance.calculateProjectIncome();
            return total * 0.03;  // Pajak 3% dari total
        }
        return emp.getSalary() * 0.03;
    }
}

// L - Liskov Substitution Principle
abstract class BirdLSP {
    public abstract void eat();
}

interface Flyable {
    void fly();
}

class Sparrow extends BirdLSP implements Flyable {
    @Override
    public void eat() {
        System.out.println("🐦 Burung pipit sedang makan");
    }
    
    @Override
    public void fly() {
        System.out.println("🐦 Burung pipit terbang");
    }
}

class PenguinLSP extends BirdLSP {
    @Override
    public void eat() {
        System.out.println("🐧 Penguin sedang makan ikan");
    }
    // Penguin tidak implement Flyable - ini benar!
}

// I - Interface Segregation Principle
interface WorkableLSP {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

class Robot implements WorkableLSP {
    @Override
    public void work() {
        System.out.println("🤖 Robot sedang bekerja");
    }
}

class HumanWorker implements WorkableLSP, Eatable, Sleepable {
    @Override
    public void work() {
        System.out.println("👨‍💼 Manusia sedang bekerja");
    }
    
    @Override
    public void eat() {
        System.out.println("🍜 Manusia sedang makan");
    }
    
    @Override
    public void sleep() {
        System.out.println("😴 Manusia sedang tidur");
    }
}

// D - Dependency Inversion Principle
interface NotificationSender {
    void send(String message, String recipient);
}

class EmailNotification implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("📧 Email ke " + recipient + ": " + message);
    }
}

class SMSNotification implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("📱 SMS ke " + recipient + ": " + message);
    }
}

class WhatsAppNotification implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("💬 WhatsApp ke " + recipient + ": " + message);
    }
}

class TelegramNotification implements NotificationSender {  // Tugas 3
    @Override
    public void send(String message, String recipient) {
        System.out.println("✈️ Telegram ke @" + recipient + ": " + message);
    }
}

class NotificationService {
    private NotificationSender sender;
    
    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }
    
    public void notifyEmployee(Employee emp, String message) {
        sender.send(message, emp.getName());
    }
    
    // Overloading untuk multiple recipients
    public void notifyEmployee(String message, Employee... employees) {
        for (Employee emp : employees) {
            sender.send(message, emp.getName());
        }
    }
}