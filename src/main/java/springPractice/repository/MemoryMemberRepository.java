package springPractice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import springPractice.domain.Member;



public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(),member);
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		//store에 값을 stream함수로 loop하면서 찾는데, 필터는 member의 이름이며,
		//인수로 받은 이름과 비교하여 찾는다. 옵셔널로 감싸져서
		//만약 없다면 Null값이 반환되고, 있으면 찾은 값을 반환한다.
		//어떻게 member를 쓸 수 있는 건지???
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();	
		
	}

	@Override
	public List<Member> findAll() {
		
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
	

}
