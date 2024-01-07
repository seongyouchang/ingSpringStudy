package hello.core.order;
// public으로 외부에 공개한거라 파일명과 클래스명이 맞아야 한다
public interface OrderService {
    // 설계에서 주문생성부분 회원Id 회원명 아이템가격을 클라이언트에서 서비스로 주문생성 후 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
