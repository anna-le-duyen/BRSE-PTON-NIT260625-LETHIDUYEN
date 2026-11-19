package presentation;

import bussiness.BorrowService;
import model.BorrowCard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class BorrowView {
    Scanner scanner = new Scanner(System.in);
    BorrowService borrowService = new BorrowService();
    private String input(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    private void showList(List<BorrowCard> borrowCards) {
        for (BorrowCard card : borrowCards) {
            System.out.println(card);
        }
    }
    public void run() {
        while (true) {
            System.out.println("=============== LIBRARY MANAGEMENT ===============");
            System.out.println("1. Danh sách tất cả phiếu mượn");
            System.out.println("2. Thêm mới phiếu mượn");
            System.out.println("3. Cập nhật thông tin phiếu mượn");
            System.out.println("4. Xoá phiếu mượn");
            System.out.println("5. Tìm kiếm phiếu mượn theo tên độc giả");
            System.out.println("6. Tìm kiếm phiếu mượn theo tên sách");
            System.out.println("7. Thoát");
            String choice = input("Nhập lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case "1":
                        showList(borrowService.listBorrowCards());
                        break;

                    case "2":
                        BorrowCard borrowCard = new BorrowCard();
                        borrowCard.setBookTitle(input("Nhập tên sách: "));
                        borrowCard.setBorrowerName(input("Nhập tên độc giả: "));
                        borrowCard.setBorrowDate(LocalDateTime.parse(input("Nhập ngày mượn (yyyy-MM-ddTHH:mm:ss): ")));
                        borrowCard.setReturnDeadline(LocalDateTime.parse(input("Nhập ngày phải trả (yyyy-MM-ddTHH:mm:ss): ")));
                        borrowCard.setQuantity(Integer.parseInt(input("Nhập số lượng: ")));
                        borrowCard.setStatus(input("Nhập trạng thái: "));
                        borrowService.addBorrowCard(borrowCard);
                        System.out.println("Lưu thông tin mượn thành công.");
                        break;

                    case "3":
                        BorrowCard updateCard = new BorrowCard();
                        updateCard.setBookTitle(input("Nhập tên sách: "));
                        updateCard.setBorrowerName(input("Nhập tên độc giả: "));
                        updateCard.setBorrowDate(LocalDateTime.parse(input("Nhập ngày mượn (yyyy-MM-ddTHH:mm:ss): ")));
                        updateCard.setReturnDeadline(LocalDateTime.parse(input("Nhập ngày trả (yyyy-MM-ddTHH:mm:ss): ")));
                        updateCard.setQuantity(Integer.parseInt(input("Nhập số lượng: ")));
                        updateCard.setStatus(input("Nhập trạng thái: "));
                        borrowService.updateBorrowCard(updateCard);
                        System.out.println("Lưu thông tin thẻ mượn thành công.");
                        break;

                    case "4":
                        long id = Long.parseLong(input("Nhập ID muốn xoá: "));
                        borrowService.deleteBorrowCard((int) id);
                        System.out.println("Xoá thông tin thẻ mượn thành công.");
                        break;

                    case "5":
                        String borrowerName = input("Tìm kiếm theo tên độc giả: ");
                        showList(borrowService.searchBorrowCardsByBorrowerName(borrowerName));
                        break;

                    case "6":
                        String bookTitle = input("Tìm kiếm theo tên sách: ");
                        showList(borrowService.searchBorrowCardsByBookTitle(bookTitle));
                        break;

                    case "7":
                        System.out.println("Đang thoát...");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Có lỗi xảy ra: " + e.getMessage());
            }
        }
    }
}
