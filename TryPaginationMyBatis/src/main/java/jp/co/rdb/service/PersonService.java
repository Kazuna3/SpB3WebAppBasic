package jp.co.rdb.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rdb.entity.Person;
import jp.co.rdb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PersonService {

	private final PersonRepository personRepository;

	public Page<Person> selectAll(Pageable pageable) {

		RowBounds rowBounds = new RowBounds(
				(int) pageable.getOffset(),
				pageable.getPageSize());

		List<Person> persons = personRepository.selectAll(rowBounds);
		Long total = personRepository.count();

		return new PageImpl<>(persons, pageable, total);

	}

}
