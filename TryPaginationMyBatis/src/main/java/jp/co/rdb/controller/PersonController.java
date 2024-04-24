package jp.co.rdb.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.rdb.entity.Person;
import jp.co.rdb.form.SearchConditionForm;
import jp.co.rdb.service.PersonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PersonController {

	private final PersonService personService;
	private final HttpSession session;

	@GetMapping("/list")
	public String getAllPersons(
	// @formatter:off
			Model model
		,	@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable
	// @formatter:on
	) {

		// セッション内の情報をクリアする。
		session.invalidate();

		Page<Person> persons = personService.selectAll(pageable);
		model.addAttribute("persons", persons);

		SearchConditionForm searchConditionForm = new SearchConditionForm();
		model.addAttribute("searchConditionForm", searchConditionForm);
		session.setAttribute("searchConditionForm", searchConditionForm);

		return "listPerson";

	}

	@PostMapping("/search/name")
	public String searchName(
	// @formatter:off
			@ModelAttribute SearchConditionForm searchConditionForm
		,	BindingResult result
		,	Model model
		,	@PageableDefault(page = 0, size = 5) Pageable pageable
	// @formatter:on
	) {

		session.setAttribute("likeConditionForm", false);
		session.setAttribute("searchConditionForm", searchConditionForm);

		Page<Person> persons = personService.findByShimei(pageable, searchConditionForm.getShimei());
		model.addAttribute("persons", persons);

		return "listPerson";

	}

	@PostMapping("/search/name/like")
	public String searchNameLike(
	// @formatter:off
			@ModelAttribute SearchConditionForm searchConditionForm
		,	BindingResult result
		,	Model model
		,	@PageableDefault(page = 0, size = 5) Pageable pageable
	// @formatter:on
	) {

		session.setAttribute("likeConditionForm", true);
		session.setAttribute("searchConditionForm", searchConditionForm);

		Page<Person> persons = personService.findByShimeiLike(pageable, searchConditionForm.getShimei());
		model.addAttribute("persons", persons);

		return "listPerson";

	}

	@GetMapping("/query/person")
	public String queryPerson(
	// @formatter:off
			Model model
		,	@PageableDefault(page = 0, size = 5) Pageable pageable
	// @formatter:on
	) {

		// 前回と同じ検索を実行する。
		Page<Person> persons = performTheSameSearchAsBefore(model, pageable);

		model.addAttribute("persons", persons);

		return "listPerson";

	}

	@PostMapping("/delete")
	public String delete(
	// @formatter:off
			@RequestParam Integer id
		,	Model model
		,	@PageableDefault(page = 0, size = 5) Pageable pageable
	// @formatter:on
	) {

		System.out.println("delete id［" + id + "］");
		Integer delCounter = personService.deleteById(id);
		System.out.println("delCounter［" + delCounter + "］");

		// 前回と同じ検索を実行する。
		Page<Person> persons = performTheSameSearchAsBefore(model, pageable);

		System.out.println("persons.getTotalElements()［" + persons.getTotalElements() + "］");
		System.out.println("persons.getSize()［" + persons.getSize() + "］");
		System.out.println("persons.getNumber()［" + persons.getNumber() + "］");

		if ((long) (persons.getSize() * persons.getNumber()) >= persons.getTotalElements()) {

			System.out.println("◆最終ページのデータが0件になりました！");
			return "redirect:/query/person?page=" + (persons.getNumber() - 1);

		}

		model.addAttribute("persons", persons);

		return "listPerson";

	}

	// 前回と同じ検索を実行する。 
	// Perform the same search as before.
	private Page<Person> performTheSameSearchAsBefore(
	// @formatter:off
			Model model
		,	@PageableDefault(page = 0, size = 5) Pageable pageable
	// @formatter:on
	) {

		Boolean isLike = (Boolean) session.getAttribute("likeConditionForm");
		SearchConditionForm searchConditionForm = (SearchConditionForm) session.getAttribute("searchConditionForm");
		model.addAttribute("searchConditionForm", searchConditionForm);

		Page<Person> persons;

		if (Boolean.TRUE.equals(isLike)) {

			persons = personService.findByShimeiLike(pageable, searchConditionForm.getShimei());

		} else if (Boolean.FALSE.equals(isLike)) {

			persons = personService.findByShimei(pageable, searchConditionForm.getShimei());

		} else {

			persons = personService.selectAll(pageable);

		}

		return persons;

	}

}
