use customer;
create table customer( CustomerNumber int not null auto_increment, FirstName varchar(50), LastName varchar(50), street1 varchar(30), street2 varchar(30), city varchar(50), state char(25), zipCode varchar(20), primary key (CustomerNumber));
desc customer;
create table Vendor ( VendorNumber int not null auto_increment, VendorName varchar(25), street1 varchar(50), street2 varchar(50), City varchar(50), state char(10), ZipCode varchar(15), primary key (VendorNumber));

desc Vendor;
create table Product( ProductNumber int Not null auto_increment, productName varchar(50) not null, ProductUnitCost Decimal(10,2), ProductInventory int, VendorNumber int,primary key (productNumber), foreign key (VendorNumber) references Vendor(VendorNumber));
desc Product;

create table Carrier (CarrierNumber int not null, Name varchar(25), street1 varchar(25), street2 varchar(25), City varchar(25), State varchar(25), zipCode varchar(20), nextDayRate decimal(10,2), twoDayRate decimal(10,2), weekDayRate decimal(10,2), primary key (CarrierNumber));
create table shipping (ShippingNumber int not null auto_increment, CarrierNumber int not null, ShippingCost decimal(10,2), DateShipped date, DateArrived date, primary key (ShippingNumber), foreign key (CarrierNumber) references Carrier(CarrierNumber));
create table Ordering (OrderNumber int not null auto_increment, CustomerNumber int, orderDate date, ProductNumber int, orderQuantity int, TotalCost int, ShippingNumber int, primary key (OrderNumber), foreign key (CustomerNumber) references Customer(CustomerNumber), foreign key(ProductNumber) references Product(ProductNumber), foreign key(ShippingNumber) references shipping(ShippingNumber));

show tables;
desc shipping;