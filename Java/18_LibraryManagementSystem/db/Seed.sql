use LibraryDB;

INSERT INTO [books]
    ([name], [author])
VALUES
    ('The C Programming Language', 'Dennis Ritchie'),
    ('The X Window System', 'Simon Tatham'),
    ('The Linux Kernel', 'Linus Torvalds');

INSERT INTO [book_copies]
    ([serial_number], [issuable])
VALUES
    (1, 1),
    (1, 0),
    (1, 1),
    (1, 0),
    (2, 0),
    (2, 0),
    (3, 1),
    (3, 1),
    (3, 1);

INSERT INTO [members]
    ([name], [type])
VALUES
    ('John Doe', 'student'),
    ('Jane Doe', 'faculty'),
    ('John Smith', 'student'),
    ('Jane Smith', 'faculty');
