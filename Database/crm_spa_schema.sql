-- Auto-generated MySQL schema for CRM Spa System
-- Generated with explicit constraints and logic validation

CREATE TABLE Address (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    AddressDescription TEXT,
    AddressDetail NVARCHAR(100) NOT NULL,
    Ward NVARCHAR(100) NOT NULL,
    District NVARCHAR(50) NOT NULL,
    Province NVARCHAR(50) NOT NULL,
    Country NVARCHAR(50) NOT NULL
);

CREATE TABLE Facility (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ShopName NVARCHAR(255),
    ShopType NVARCHAR(255),
    Facility NVARCHAR(255),
    Region NVARCHAR(255),
    StatusOpening ENUM('Open', 'Closed', 'Maintenance') NOT NULL,
    TotalServiceBed INT CHECK (TotalServiceBed >= 0)
);

CREATE TABLE CustomerType (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerType NVARCHAR(50),
    PriceThreshold DECIMAL(18,2) DEFAULT 0 CHECK (PriceThreshold >= 0),
    ColorPresent VARCHAR(20),
    Description NVARCHAR(255)
);

CREATE TABLE CustomerResource (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Resource NVARCHAR(50)
);

CREATE TABLE Employee (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName NVARCHAR(50),
    LastName NVARCHAR(50),
    Gender CHAR(1) CHECK (Gender IN ('M', 'F')),
    DOB DATE,
    PhoneNumber VARCHAR(20),
    Email VARCHAR(100) UNIQUE,
    HireDate DATE,
    Position NVARCHAR(100),
    AddressID INT,
    FacilityID INT,
    ManagerID INT,
    Salary DECIMAL(18,2) CHECK (Salary >= 0),
    EmploymentStatus VARCHAR(20),
    FOREIGN KEY (AddressID) REFERENCES Address(ID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(ID),
    FOREIGN KEY (ManagerID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE User (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    Firstname NVARCHAR(50) NOT NULL,
    Lastname NVARCHAR(50) NOT NULL,
    Username VARCHAR(100) UNIQUE,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    DOB DATETIME NOT NULL,
    PhoneNumber VARCHAR(11) NOT NULL UNIQUE,
    Gender BIT NOT NULL,
    AddressID INT NOT NULL,
    FacilityID INT NOT NULL,
    CustomerTypeID INT NOT NULL,
    CustomerResourceID INT NOT NULL,
    CareStaffID INT NOT NULL,
    RoleID INT NOT NULL,
    FOREIGN KEY (AddressID) REFERENCES Address(ID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(ID),
    FOREIGN KEY (CustomerTypeID) REFERENCES CustomerType(ID),
    FOREIGN KEY (CustomerResourceID) REFERENCES CustomerResource(ID),
    FOREIGN KEY (CareStaffID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE ServicePackage (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    ServiceName NVARCHAR(255),
    ServiceType NVARCHAR(255),
    ServiceDetail TEXT
);

CREATE TABLE PaymentMethod (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PaymentMethod ENUM('Cash', 'Wallet', 'PrepaidCard') NOT NULL,
    Amount BIGINT DEFAULT 0
);

CREATE TABLE `Order` (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    TimeSlot TIME,
    Date DATETIME,
    TotalSale BIGINT DEFAULT 0,
    ActualRevenue BIGINT DEFAULT 0,
    PaymentMethodID INT,
    ServicePackageID INT,
    FacilityID INT,
    FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethod(ID),
    FOREIGN KEY (ServicePackageID) REFERENCES ServicePackage(ID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(ID)
);

CREATE TABLE Wallet (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Balance DECIMAL(18,2) NOT NULL DEFAULT 0,
    Currency VARCHAR(10) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE WalletTransaction (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    WalletID INT,
    Amount DECIMAL(18,2) NOT NULL CHECK (Amount >= 0),
    Type ENUM('TopUp', 'Spend', 'Refund'),
    ReferenceOrderID INT UNIQUE,
    Note TEXT,
    FOREIGN KEY (WalletID) REFERENCES Wallet(ID),
    FOREIGN KEY (ReferenceOrderID) REFERENCES `Order`(ID)
);

CREATE TABLE Debts (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    Amount DECIMAL(18,2) CHECK (Amount >= 0),
    PaymentDeadline DATE,
    Status ENUM('Pending', 'Paid', 'Overdue'),
    ReferenceOrderID INT UNIQUE,
    Note TEXT,
    FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (ReferenceOrderID) REFERENCES `Order`(ID)
);

CREATE TABLE Vouchers (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Code VARCHAR(50) NOT NULL UNIQUE,
    Discount DECIMAL(5,2),
    Amount DECIMAL(18,2) CHECK (Amount > 0),
    Status VARCHAR(20),
    StartDate DATE,
    ExpiryDate DATE,
    Note TEXT
);

CREATE TABLE VoucherUsages (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    VoucherID INT NOT NULL,
    ReferenceOrderID INT,
    UsedAmount DECIMAL(18,2) DEFAULT 0,
    UsedAt DATETIME,
    FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (VoucherID) REFERENCES Vouchers(ID),
    FOREIGN KEY (ReferenceOrderID) REFERENCES `Order`(ID)
);

CREATE TABLE PrepaidCard (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Balance DECIMAL(18,2) DEFAULT 0,
    Currency VARCHAR(10),
    FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE PrepaidCardTransaction (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PrepaidCardID INT NOT NULL,
    Amount DECIMAL(18,2),
    Type ENUM('TopUp', 'Use', 'Refund'),
    ReferenceOrderID INT UNIQUE,
    Note TEXT,
    FOREIGN KEY (PrepaidCardID) REFERENCES PrepaidCard(ID),
    FOREIGN KEY (ReferenceOrderID) REFERENCES `Order`(ID)
);

CREATE TABLE AppInfoCheck (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    InstalledDevice NVARCHAR(255),
    Status ENUM('Installed', 'Removed', 'Inactive'),
    InstalledDate DATETIME,
    FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE FeedbackOnOrders (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT UNIQUE,
    Status ENUM('Good', 'Bad', 'Neutral'),
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    FeedbackContent TEXT,
    Description TEXT,
    FOREIGN KEY (OrderID) REFERENCES `Order`(ID)
);

CREATE TABLE CostOnOrder (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT UNIQUE,
    ServiceCost DECIMAL(18,2),
    Surcharge DECIMAL(18,2),
    ShiftEmployeeID INT,
    PerformingStaffID INT,
    EmployeeSalary DECIMAL(18,2),
    Discount DECIMAL(18,2),
    PriceIncrease DECIMAL(18,2),
    FOREIGN KEY (OrderID) REFERENCES `Order`(ID),
    FOREIGN KEY (ShiftEmployeeID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (PerformingStaffID) REFERENCES Employee(EmployeeID)
);