package hello.core.lifecycle;

public class NetworkClient {
    private String url;
    public  NetworkClient(){
        System.out.println("url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connent = " + url);
    }

    public void call(String msg){
        System.out.println("call = " + url + "msg = " + msg);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }
}
