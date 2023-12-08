package com.seclore.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.seclore.dao.BookCopyDAO;
import com.seclore.dao.BookDAO;
import com.seclore.dao.BookIssueDAO;
import com.seclore.dao.MemberDAO;
import com.seclore.factory.ConnectionFactory;
import com.seclore.pojo.Book;
import com.seclore.pojo.BookCopy;
import com.seclore.pojo.BookIssue;
import com.seclore.pojo.Member;
import com.seclore.util.IOUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationMain {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        String name;
        boolean isFaculty;
        int memberCode;
        int bookCopyCode;
        int bookSerialNumber;
        int bookIssueCode;
        boolean isIssuable;
        List<BookIssue> bookIssues;
        Member member;
        MemberDAO memberDAO;
        BookDAO bookDAO;
        BookCopyDAO bookCopyDAO;
        BookIssue bookIssue;
        Book book;
        BookCopy bookCopy;
        BookIssueDAO bookIssueDAO;
        boolean success;
        boolean continueLoop = true;
        Connection connection;
        IOUtil ioUtil = new IOUtil();

        do {
            connection = ConnectionFactory.getConnection();
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.fatal("Failed to start transaction.");
                LOGGER.fatal(e);
            }
            switch (ioUtil.getInt(
                    "---\nMain Menu:\n  1. Masters\n  2. Transactions\n  3. Reports\n  4. Exit\nEnter choice: ")) {
                case 1:
                    switch (ioUtil.getChar("---\nMaster Menu:\n  a. Members\n  b. Books\nEnter choice: ")) {
                        case 'a':
                            switch (ioUtil.getInt(
                                    "---\nMember Menu:\n  1. Add new member\n  2. Update existing member\n  3. Delete member\nEnter choice: ")) {
                                case 1:
                                    name = ioUtil.getString("Enter member name: ");
                                    isFaculty = ioUtil.getBoolean("Is member a faculty? (y/n): ");
                                    member = new Member(name, isFaculty ? "faculty" : "student");
                                    memberDAO = new MemberDAO(connection);
                                    success = memberDAO.addNewMember(member);
                                    System.out.println(success ? "Member added successfully" : "Member not added");
                                    if (success) {
                                        System.out.println(member);
                                    } else {
                                        tryRollback(connection);
                                    }
                                    break;
                                case 2:
                                    memberCode = ioUtil.getInt("Enter member code: ");
                                    memberDAO = new MemberDAO(connection);
                                    member = memberDAO.getMemberByCode(memberCode);

                                    if (member == null) {
                                        System.out.println("Member not found.");
                                        break;
                                    }

                                    member.setName(ioUtil.getString("Enter updated member name: "));
                                    success = memberDAO.updateMember(member);
                                    System.out.println(success ? "Member updated successfully" : "Member not updated");
                                    if (success) {
                                        System.out.println(member);
                                    } else {
                                        tryRollback(connection);
                                    }

                                    break;
                                case 3:
                                    memberCode = ioUtil.getInt("Enter member code: ");
                                    memberDAO = new MemberDAO(connection);
                                    member = memberDAO.getMemberByCode(memberCode);
                                    if (member == null) {
                                        System.out.println("Member not found.");
                                        break;
                                    }
                                    System.out.println(member);
                                    success = memberDAO.deleteMember(member);
                                    System.out.println(success ? "Member deleted successfully" : "Member not deleted");
                                    if (!success) {
                                        tryRollback(connection);
                                    }
                                    break;
                            }
                            break;
                        case 'b':
                            switch (ioUtil.getInt(
                                    "---\nBook Menu:\n  1. Add book to library\n  2. Mark book as not issuable\n  3. Delete book\nEnter choice: ")) {
                                case 1:
                                    String bookName = ioUtil.getString("Enter book name: ");
                                    String bookAuthor = ioUtil.getString("Enter book author: ");
                                    int numberOfBooks = ioUtil.getInt("Enter number of book copies: ");
                                    int numberOfIssuableBooks = ioUtil.getInt("Enter number of issuable book copies: ");
                                    book = new Book(bookName, bookAuthor);
                                    bookDAO = new BookDAO(connection);
                                    success = bookDAO.addNewBook(book);
                                    if (!success) {
                                        System.out.println("Cannot add book.");
                                        tryRollback(connection);
                                        break;
                                    }

                                    bookCopyDAO = new BookCopyDAO(connection);
                                    success = bookCopyDAO.addNewBookCopies(book, numberOfBooks, numberOfIssuableBooks);

                                    System.out.println(success ? "Book and its copies added successfully."
                                            : "Book added but could not add it's copies.");
                                    if (success) {
                                        System.out.println(book);
                                        List<BookCopy> bookCopies = bookCopyDAO.getAllBookCopiesByBook(book);
                                        System.out.println("Added copies are: ");
                                        for (BookCopy copy : bookCopies) {
                                            System.out.println(copy);
                                        }
                                    } else {
                                        tryRollback(connection);
                                    }

                                    break;
                                case 2:
                                    bookCopyCode = ioUtil.getInt("Enter book code: ");
                                    isIssuable = ioUtil.getBoolean("Is the book copy issuable? (y/n): ");
                                    bookCopyDAO = new BookCopyDAO(connection);
                                    bookCopy = bookCopyDAO.getBookCopyByCode(bookCopyCode);
                                    if (bookCopy == null) {
                                        System.out.println("Book copy not found.");
                                        break;
                                    }
                                    bookCopy.setIssuable(isIssuable);
                                    success = bookCopyDAO.updateBookCopy(bookCopy);
                                    System.out.println(
                                            success ? "Book copy updated successfully." : "Book copy not updated.");
                                    System.out.println(bookCopy);
                                    if (!success) {
                                        tryRollback(connection);
                                    }
                                    break;
                                case 3:
                                    bookSerialNumber = ioUtil.getInt("Enter book serial number: ");
                                    bookDAO = new BookDAO(connection);
                                    book = bookDAO.getBookBySerialNumber(bookSerialNumber);
                                    if (book == null) {
                                        System.out.println("Book not found.");
                                        break;
                                    }
                                    System.out.println(book);
                                    success = bookDAO.deleteBook(book);
                                    System.out.println(success ? "Book deleted successfully." : "Book not deleted.");
                                    if (!success) {
                                        tryRollback(connection);
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    switch (ioUtil
                            .getChar("---\nTransaction Menu:\n  y. Issue Book\n  z. Return Book\nEnter choice: ")) {
                        case 'y':
                            bookSerialNumber = ioUtil.getInt("Enter book serial number: ");
                            bookDAO = new BookDAO(connection);
                            bookCopyDAO = new BookCopyDAO(connection);
                            book = bookDAO.getBookBySerialNumber(bookSerialNumber);
                            if (book == null) {
                                System.out.println("Book not found.");
                                break;
                            }
                            System.out.println(book);
                            memberCode = ioUtil.getInt("Enter member code: ");
                            memberDAO = new MemberDAO(connection);
                            member = memberDAO.getMemberByCode(memberCode);
                            if (member == null) {
                                System.out.println("Member not found.");
                                break;
                            }
                            System.out.println(member);
                            bookCopy = bookCopyDAO.getIssueableBookCopy(book);

                            if (bookCopy == null) {
                                System.out.println("No issueable copy found.");
                                break;
                            }

                            bookIssueDAO = new BookIssueDAO(connection);
                            success = bookIssueDAO.addBookIssue(bookCopy, member);
                            System.out.println(success ? "Book issued successfully." : "Could not issue book.");
                            if (!success) {
                                tryRollback(connection);
                            }
                            break;
                        case 'z':
                            bookIssueCode = ioUtil.getInt("Enter book issue code: ");
                            bookIssueDAO = new BookIssueDAO(connection);
                            bookIssue = bookIssueDAO.getBookIssueByCode(bookIssueCode);
                            if (bookIssue == null) {
                                System.out.println("Book Issue not found.");
                                break;
                            }
                            System.out.println(bookIssue);
                            success = bookIssueDAO.returnIssuedBook(bookIssue);
                            System.out.println(success ? "Book returned successfully." : "Book could not be returned.");
                            if (!success) {
                                tryRollback(connection);
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    switch (ioUtil.getChar(
                            "---\nReport Menu:\n  p. Book issue details\n  q. Book return details\nEnter choice: ")) {
                        case 'p':
                            bookIssueDAO = new BookIssueDAO(connection);
                            bookIssues = bookIssueDAO.getBookIssuesByStatus("issued");
                            System.out.println("Issued books: ");
                            System.out.println("Code\tBook\tMember\tIssueDate");
                            for (BookIssue issue : bookIssues) {
                                System.out.println(issue.getCode() + "\t" + issue.getBookCopy().getBook().getName()
                                        + "\t" + issue.getMember().getName() + "\t" + issue.getDateOfIssue());
                            }
                            break;
                        case 'q':
                            bookIssueDAO = new BookIssueDAO(connection);
                            bookIssues = bookIssueDAO.getBookIssuesByStatus("returned");
                            System.out.println("Returned books: ");
                            System.out.println("Code\tBook\tMember\tIssued On\tReturned On");
                            for (BookIssue issue : bookIssues) {
                                System.out.println(issue.getCode() + "\t" + issue.getBookCopy().getBook().getName()
                                        + "\t" + issue.getMember().getName() + "\t" + issue.getDateOfIssue() + "\t"
                                        + issue.getDateOfReturn());
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");

                    }
                    break;
                case 4:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            try {
                connection.commit();
            } catch (SQLException e) {
                LOGGER.fatal("Failed to commit transaction.");
                LOGGER.fatal(e);
            }
            ConnectionFactory.closeConnection(connection);
        } while (continueLoop);
    }

    private static void tryRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.fatal("Failed to rollback transaction.");
            LOGGER.fatal(e);
        }
    }
}
