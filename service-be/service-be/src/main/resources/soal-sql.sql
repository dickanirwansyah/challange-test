--- CREATE TABLE--
create table customer(
	cust_id bigserial,
	cust_firstname varchar(30),
	cust_lastname varchar(30),
	cust_birthdate date,
	cust_gender varchar(2),
	cust_address varchar(50),
	cust_city varchar(50),
	cust_postcode varchar(10)
);

create table transactions(
	trs_id bigserial,
	trs_from_account varchar(100),
	trs_date date,
	trs_amount numeric,
	trs_type varchar(5)
);

create table transactions_transfer(
	trs_id bigserial,
	trs_to_account varchar(15),
	trs_status varchar(5)
);

create table account(
	acc_number varchar(15) unique,
	acc_owner bigint,
	acc_date_created date,
	acc_balance numeric
);

--initiate data customer
select * from customer;
insert into customer (cust_firstname, cust_lastname, cust_birthdate, cust_gender, cust_address, cust_city, cust_postcode)
values ('dicka', 'nirwansyah', to_date('1996-03-19', 'yyyy-mm-dd'), '1', 'Bogor', 'Bogor','12430');
insert into customer (cust_firstname, cust_lastname, cust_birthdate, cust_gender, cust_address, cust_city, cust_postcode)
values ('dicky', 'adriansyah', to_date('1994-02-20', 'yyyy-mm-dd'), '1', 'Bekasi', 'Bekasi','12431');
insert into customer (cust_firstname, cust_lastname, cust_birthdate, cust_gender, cust_address, cust_city, cust_postcode)
values ('maya', 'afrianti', to_date('1996-04-12', 'yyyy-mm-dd'), '2', 'Jonggol', 'Jonggol','62430');
insert into customer (cust_firstname, cust_lastname, cust_birthdate, cust_gender, cust_address, cust_city, cust_postcode)
values ('jhon', 'michael', to_date('1996-04-12', 'yyyy-mm-dd'), '1', 'Depok', 'Depok','62430');

--initiate data account--
insert into account (acc_number, acc_owner, acc_date_created, acc_balance) values ('00088892993', 1, to_date('2023-04-11','yyyy-mm-dd'), 20000000);
insert into account (acc_number, acc_owner, acc_date_created, acc_balance) values ('00088892994', 2, to_date('2023-04-11','yyyy-mm-dd'), 10000000);
insert into account (acc_number, acc_owner, acc_date_created, acc_balance) values ('00088892995', 3, to_date('2023-04-11','yyyy-mm-dd'), 15000000);
insert into account (acc_number, acc_owner, acc_date_created, acc_balance) values ('00088892996', 4, to_date('2023-04-11','yyyy-mm-dd'), 16000000);


--initiate data make transaction & transaction transfer & update amount--
insert into transactions (trs_from_account, trs_date, trs_amount, trs_type) values ('00088892993', to_date('2023-04-11', 'yyyy-mm-dd'), 6000, 'TF');
insert into transactions_transfer (trs_to_account, trs_status) values ('00088892995', 1);
update account set acc_balance=19994000 where acc_number ='00088892993';
--transaksi jhone michael
insert into transactions (trs_from_account, trs_date, trs_amount, trs_type) values ('00088892996', to_date('2023-04-11', 'yyyy-mm-dd'), 6000, 'TF');
insert into transactions_transfer (trs_to_account, trs_status) values ('00088892995', 1);
update account set acc_balance=1594000 where acc_number ='00088892996';

-------------- SOAL ---------------------
1. --rekap jumlah account number setiap nasabah
select count(a.acc_number) from account a join customer c on a.acc_owner = c.cust_id;

2. --transaksi jhon michael
select t1.trs_id, t1.trs_from_account, t2.trs_to_account,
t1.trs_date,
t1.trs_amount, t1.trs_type, t2.trs_status
from transactions t1 inner join transactions_transfer t2 on t1.trs_id = t2.trs_id
where trs_from_account='00088892996' and trs_date=to_date('2022-04-11', 'yyyy-mm-dd');
