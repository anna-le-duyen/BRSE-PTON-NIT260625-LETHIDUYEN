create database if not exists db_library_management;
use db_library_management;
create table if not exists borrow_cards(
    card_id serial primary key,
    book_title varchar(150) not null,
    borrower_name varchar(100) not null,
    borrow_date timestamp not null,
    return_deadline timestamp not null ,
    quantity int not null check ( quantity > 0),
    status varchar(30) not null check (status in ('Đang mượn', 'Đã trả', 'Quá hạn'))
);

delimiter $$
create procedure sp_get_all()
begin
    select * from borrow_cards;
end $$
create procedure sp_add(
    in p_book_title varchar(150),
    in p_borrower_name varchar(100),
    in p_borrow_date timestamp,
    in p_return_deadline timestamp,
    in p_quantity int,
    in p_status varchar(30)
)
begin
    insert into borrow_cards (book_title, borrower_name, borrow_date, return_deadline, quantity, status)
    values (p_book_title, p_borrower_name, p_borrow_date, p_return_deadline, p_quantity, p_status);
end $$

create procedure sp_update(
    in p_card_id int,
    in p_book_title varchar(150),
    in p_borrower_name varchar(100),
    in p_borrow_date timestamp,
    in p_return_deadline timestamp,
    in p_quantity int,
    in p_status varchar(30)
)
begin
    update borrow_cards
    set book_title = p_book_title,
        borrower_name = p_borrower_name,
        borrow_date = p_borrow_date,
        return_deadline = p_return_deadline,
        quantity = p_quantity,
        status = p_status
    where card_id = p_card_id;
end $$

create procedure sp_delete(
    in p_card_id int
)
begin
    delete from borrow_cards where card_id = p_card_id;
end $$

 create procedure sp_search_by_borrower_name(
    in p_borrower_name varchar(100)
)
begin
    select * from borrow_cards where borrower_name like concat('%', p_borrower_name, '%');
end $$

create procedure sp_search_by_book_title(
    in p_book_title varchar(150)
)
begin
    select * from borrow_cards where book_title like concat('%', p_book_title, '%');
end $$