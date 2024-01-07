package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // 저장소니까 Map이 필요하다
    // store에 여러군데에서 접근하면 동시성 문제가 생기니 concurrentHashMap을 사용해야하는게 맞다 (예제니까 단순HashMap을 쓴다)
    private static Map<Long,Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long MemberId) {
        return store.get(MemberId);
    }
}
