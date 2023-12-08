use LibraryDB;

DROP TABLE issues;
DROP TABLE members;
DROP TABLE book_copies;
DROP TABLE books;

SELECT *
FROM books;

SELECT *
FROM book_copies;

SELECT *
FROM issues;

DELETE FROM issues;

SELECT books.serial_number, books.name, books.author, book_copies.code, book_copies.issuable
FROM book_copies JOIN books
    ON book_copies.serial_number = books.serial_number
WHERE book_copies.code = 1;