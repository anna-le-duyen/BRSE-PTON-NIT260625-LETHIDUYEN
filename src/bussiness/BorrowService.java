package bussiness;

import model.BorrowCard;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowService {
    public void addBorrowCard(BorrowCard borrowCard) throws Exception {
       try(Connection connection = database.DBConnection.getConnection();
           CallableStatement cs = connection.prepareCall("{call sp_add(?,?,?,?,?,?)}")
       ) {
              cs.setString(1, borrowCard.getBookTitle());
              cs.setString(2, borrowCard.getBorrowerName());
              cs.setObject(3, borrowCard.getBorrowDate());
              cs.setObject(4, borrowCard.getReturnDeadline());
              cs.setInt(5, borrowCard.getQuantity());
              cs.setString(6, borrowCard.getStatus());

              cs.executeUpdate();
       }
    }

    public List<BorrowCard> listBorrowCards() throws Exception {
        List<BorrowCard> borrowCards = new ArrayList<>();
        try(Connection connection = database.DBConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{call sp_get_all()}");
            ResultSet rs = cs.executeQuery()
            ) {
            while (rs.next()) {
                borrowCards.add(readCard(rs));
            }
        }
        return borrowCards;
    }
    public void deleteBorrowCard(int id) throws Exception {
        try(Connection connection = database.DBConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{call sp_delete(?)}")
        ) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }
    public void updateBorrowCard(BorrowCard borrowCard) throws Exception {
        try(Connection connection = database.DBConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{call sp_update(?,?,?,?,?,?,?)}")
        ) {
            cs.setLong(1, borrowCard.getCardId());
            cs.setString(2, borrowCard.getBookTitle());
            cs.setString(3, borrowCard.getBorrowerName());
            cs.setObject(4, borrowCard.getBorrowDate());
            cs.setObject(5, borrowCard.getReturnDeadline());
            cs.setInt(6, borrowCard.getQuantity());
            cs.setString(7, borrowCard.getStatus());

            cs.executeUpdate();
        }
    }
    public List<BorrowCard> searchBorrowCardsByBorrowerName(String borrowerName) throws Exception {
        List<BorrowCard> borrowCards = new ArrayList<>();
        try(Connection connection = database.DBConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{call sp_search_by_borrower_name(?)}")
        ) {
            cs.setString(1, borrowerName);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                borrowCards.add(readCard(rs));
            }
        }
        return borrowCards;
    }

    public List <BorrowCard> searchBorrowCardsByBookTitle(String bookTitle) throws Exception {
        List<BorrowCard> borrowCards = new ArrayList<>();
        try(Connection connection = database.DBConnection.getConnection();
            CallableStatement cs = connection.prepareCall("{call sp_search_by_book_title(?)}")
        ) {
            cs.setString(1, bookTitle);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                borrowCards.add(readCard(rs));
            }
        }
        return borrowCards;
    }

    private BorrowCard readCard(ResultSet rs) throws Exception {
        BorrowCard borrowCard = new BorrowCard();
        borrowCard.setBookTitle(rs.getString("book_title"));
        borrowCard.setBorrowerName(rs.getString("borrower_name"));
        borrowCard.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
        borrowCard.setReturnDeadline(rs.getTimestamp("return_deadline").toLocalDateTime());        borrowCard.setQuantity(rs.getInt("quantity"));
        borrowCard.setStatus(rs.getString("status"));
        return borrowCard;
    }
}
