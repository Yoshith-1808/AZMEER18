# 🔒 Password Manager - Security & Encryption Module (Member 4)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![H2 Database](https://img.shields.io/badge/H2-2.2.224-orange.svg)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Professional-grade **encryption module** for enterprise password management system with AES-256, BCrypt, and security auditing.

## ✨ **Key Features**

| Feature | Status | Description |
|---------|--------|-------------|
| 🔐 **AES-256 Encryption** | ✅ Complete | Master password-derived encryption |
| 🛡️ **BCrypt Hashing** | ✅ Complete | Secure master password storage |
| 📊 **Password Strength** | ✅ Complete | Real-time analysis (Weak/Medium/Strong/Very Strong) |
| 🔍 **Security Audit** | ✅ Complete | Weak/reused/old password detection |
| 🏗️ **REST APIs** | ✅ Complete | Production-ready endpoints |

## 🚀 **Quick Start**

### **Prerequisites**
- Java 17+
- Maven 3.6+
- IntelliJ IDEA 2025.3+

### **Run Locally**
```bash
mvn clean install
mvn spring-boot:run
```
`
```bash
App starts: http://localhost:8080
```

## 🔗 REST API Documentation

| Method | Endpoint | Description | Example |
|--------|----------|-------------|----------|
| GET | `/api/security/strength/{password}` | Password strength | `GET /api/security/strength/password123` |
| POST | `/api/security/encrypt` | Encrypt with master key | See below |
| POST | `/api/security/decrypt` | Decrypt with master key | See below |
| GET | `/api/security/audit/{userId}` | Security audit | `GET /api/security/audit/1` |

Encrypt Example
```bash
curl -u user:ebd2b6f1-b92e-4be7-a201-791f5b80d960 \
-H "Content-Type: application/json" \
-d '{"masterPassword":"MyMaster123!","password":"facebookSecret"}' \
http://localhost:8080/api/security/encrypt
```
## 🔐 Authentication

```text
Username: user
Password: ebd2b6f1-b92e-4be7-a201-791f5b80d960
```

---

## 🗄️ H2 Database Console

http://localhost:8080/h2-console

```text
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)
```

---

## 🏗️ Project Structure

```text
src/main/java/com/azmeer/passwordmanager/
├── SecurityModuleApplication.java
├── controller/SecurityController.java
├── service/
│   ├── EncryptionService.java
│   ├── PasswordStrengthService.java
│   └── SecurityAuditService.java
├── entity/
│   ├── User.java
│   └── PasswordEntry.java
└── repository/
    ├── UserRepository.java
    └── PasswordEntryRepository.java
```

---

## 📊 Password Strength Algorithm

```text
Length (8+): +2 points
Uppercase: +1
Lowercase: +1
Numbers: +1
Symbols: +2

0-2  = Weak
3-4  = Medium
5-6  = Strong
7+   = Very Strong
```

---

## 🧪 API Tests

```bash
# Strength test
curl -u user:ebd2b6f1-b92e-4be7-a201-791f5b80d960 \
  http://localhost:8080/api/security/strength/password123

# → "Weak" ✅
```

---

## 👥 Team Integration

```java
@Autowired
private EncryptionService encryptionService;

String encrypted = encryptionService.encryptPassword("secret", "master123");
String decrypted = encryptionService.decryptPassword(encrypted, "master123");
```

---

## 📈 Production

```bash
mvn clean package -DskipTests
java -jar target/*.jar
```

# Professional Encryption Module - Member 4 Complete 🚀

---

## 🚀 NOW COMMIT & PUSH:

```bash
# Terminal (IntelliJ bottom):
git add README.md
git commit -m "Fixed README.md - Clean professional version"
git push origin main
```

---

## ✅ SUCCESS =

```text
✅ GitHub shows CLEAN README.md (no conflict markers)
✅ Bottom: "Git: main" (green)
✅ https://github.com/Yoshith-1808/AZMEER18 ← PERFECT!
```