package jp.co.rdb.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.rdb.entity.Person;
import jp.co.rdb.service.PersonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PersonController {

	private final PersonService personService;

	@GetMapping("/list")
	public String getAllPersons(
		Model model,
		@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable
	) {

		Page<Person> persons = personService.selectAll(pageable);
		model.addAttribute("persons", persons);

		return "listPerson";

	}

}
