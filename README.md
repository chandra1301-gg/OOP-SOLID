
---

## 🎯 Fitur yang Dipelajari

### Object-Oriented Programming (OOP)
| Konsep | Implementasi | Status |
|--------|--------------|--------|
| **Encapsulation** | Private fields + getter/setter di class `Employee` | ✅ |
| **Inheritance** | `Manager`, `Intern`, `Freelance` extends `Employee` | ✅ |
| **Polymorphism** | Method `work()` dioverride setiap child class | ✅ |
| **Abstraction** | Abstract class `Department` | ✅ |

### SOLID Principles
| Prinsip | Implementasi | Status |
|---------|--------------|--------|
| **S**ingle Responsibility | `EmployeeRepository`, `EmailService`, `ReportGenerator` | ✅ |
| **O**pen/Closed | Interface `TaxCalculator` dengan berbagai implementasi | ✅ |
| **L**iskov Substitution | `BirdLSP` abstract class + `Flyable` interface | ✅ |
| **I**nterface Segregation | Interface `Workable`, `Eatable`, `Sleepable` | ✅ |
| **D**ependency Inversion | `NotificationService` tergantung `NotificationSender` (abstraksi) | ✅ |

> **Catatan:** Dependency Inversion berarti class bergantung pada interface/abstraksi, bukan pada implementasi konkrit.

---

## 🛠️ Tech Stack
- **Java 24** - Bahasa pemrograman utama (versi terbaru)
- **VS Code** - Code editor
- **Git** - Version control

---

## 🚀 Cara Menjalankan

### Prasyarat
- Java JDK 17 atau lebih baru (saya pakai Java 24)
- Git (opsional, untuk clone)

### Langkah-langkah

```bash
# Clone repository
git clone https://github.com/chandra1301-gg/OOP-SOLID.git

# Masuk ke folder
cd OOP-SOLID

# Compile program
javac BelajarLengkap.java

# Jalankan program
java BelajarLengkap

