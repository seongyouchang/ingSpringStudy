package hello.core.singleton;

public class SingletonService {
    //자기자신을 선언하는 방식은 관례상 많이 쓴다 자기자신을 내부에 private으로 하나 가지고 있는데 static으로 가지고있으면 Class레벨에
    //올라가기 때문에 딱 하나만 존재하게 된다
    private static final SingletonService instance = new SingletonService();

    //jvm 자바가 뜰 때 SingleService static영역에 오른쪽 new라고 되어있는 얘를 내부적으로 실행해서 객체를 생성하고,
    // 자기자신을 생성해서 instance에 참조로 넣어준다
    // 자기자신을 instance를 하나 생성해서 안에 들어가 있는것
    public static SingletonService getInstance(){
        return instance;
    }

    // private로 해야 다른 쪽에서 생성하는것을 막을 수 있다
    // 다른 쪽에서 SingletonService를 계속 생성하면 안되니까
    private SingletonService(){
    }

    private void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
