use LibraryDB;

CREATE TABLE [books]
(
  [serial_number] int PRIMARY KEY IDENTITY(101, 1),
  [name] varchar(50),
  [author] varchar(50)
)

CREATE TABLE [book_copies]
(
  [code] int PRIMARY KEY IDENTITY(101, 1),
  [serial_number] int FOREIGN KEY ([serial_number]) REFERENCES [books] ([serial_number]) ON DELETE CASCADE,
  [issuable] bit
)

CREATE TABLE [members]
(
  [code] int PRIMARY KEY IDENTITY(101, 1),
  [name] varchar(50),
  [type] varchar(10) NOT NULL CHECK ([type] IN ('student', 'faculty'))
)

CREATE TABLE [issues]
(
  [code] int PRIMARY KEY IDENTITY(101, 1),
  [book_code] int FOREIGN KEY ([book_code]) REFERENCES [book_copies] ([code]) ON DELETE CASCADE,
  [member_code] int FOREIGN KEY ([member_code]) REFERENCES [members] ([code]) ON DELETE CASCADE,
  [date_of_issue] date,
  [date_of_return] date,
  [status] varchar(10) NOT NULL CHECK ([status] IN ('issued', 'returned'))
)
