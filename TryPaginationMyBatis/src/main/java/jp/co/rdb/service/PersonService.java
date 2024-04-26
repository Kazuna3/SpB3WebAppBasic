package jp.co.rdb.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jp.co.rdb.entity.Person;
import jp.co.rdb.form.PersonForm;
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
		Long total = personRepository.selectAllCount();

		return new PageImpl<>(persons, pageable, total);

	}

	public Page<Person> findByShimei(
	// @formatter:off
			Pageable pageable
		,	String shimei
	// @formatter:on
	) {

		RowBounds rowBounds = new RowBounds(
				(int) pageable.getOffset(),
				pageable.getPageSize());

		List<Person> persons = personRepository.findByShimei(rowBounds, shimei);
		Long total = personRepository.findByShimeiCount(shimei);

		return new PageImpl<>(persons, pageable, total);

	}

	public Page<Person> findByShimeiLike(
	// @formatter:off
			Pageable pageable
		,	String shimei
	// @formatter:on
	) {

		RowBounds rowBounds = new RowBounds(
				(int) pageable.getOffset(),
				pageable.getPageSize());

		List<Person> persons = personRepository.findByShimeiLike(rowBounds, shimei);
		Long total = personRepository.findByShimeiLikeCount(shimei);

		return new PageImpl<>(persons, pageable, total);

	}

	public Integer deleteById(Integer id) {

		return personRepository.deleteById(id);

	}

	public Integer insert(String shimei) {

		return personRepository.insert(shimei);

	}

	/**
	 * 引数の文字列の文字が、半角スペース または 全角スペースのみであれば True を、そうでなければ False を返す。
	 * @param str 検査対象文字列
	 * @return Boolean 
	 */
	public Boolean isOnlySpace(String str) {

		Pattern pattern = Pattern.compile("^[　 ]+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.find();

	}

	public boolean isValid(
	// @formatter:off
			PersonForm personForm
		,	BindingResult result
	// @formatter:on
	) {

		boolean ans = true;

		if (isOnlySpace(personForm.getShimei())) {

			FieldError fieldError = new FieldError(
					result.getObjectName(),
					"shimei",
					"半角または全角のみの氏名はエラーです。");

			result.addError(fieldError);

			ans = false;

		}

		return ans;

	}

}
