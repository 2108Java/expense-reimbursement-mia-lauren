insert into users (username, password, user_type)
	values ('laurenpena', '2108Java', 'employee');

insert into users (username, password, user_type)
	values ('employee', 'employee', 'employee');

insert into users (username, password, user_type)
	values ('finanace', 'manager', 'finance manager');

insert into tickets (username, expense_type, amount, description, submitted_on, status) 
	values ('laurenpena', 'other', 10, null, now(), 'pending');
	
select * from users;

select * from tickets;