package model;

import java.time.LocalDateTime;

public class BorrowCard {
    private long cardId;
    private String bookTitle;
    private String borrowerName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDeadline;
    private int quantity;
    private String status;

    public BorrowCard() {
    }

    public BorrowCard(long cardId, String bookTitle, String borrowerName, LocalDateTime borrowDate, LocalDateTime returnDeadline, int quantity, String status) {
        this.cardId = cardId;
        this.bookTitle = bookTitle;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.returnDeadline = returnDeadline;
        this.quantity = quantity;
        this.status = status;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDateTime returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "BorrowCard{" +
                "cardId=" + cardId +
                ", bookTitle='" + bookTitle + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDeadline=" + returnDeadline +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
