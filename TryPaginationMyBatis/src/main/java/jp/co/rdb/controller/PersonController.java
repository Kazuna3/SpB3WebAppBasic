package jp.co.rdb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.rdb.entity.Person;
import jp.co.rdb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PersonController {

	private final PersonRepository personRepository;

	@GetMapping("/list")
	public String getAllPersons(
		Model model
	) {

		List<Person> persons = personRepository.selectAll();
		model.addAttribute("persons", persons);

		return "listPerson";

	}

}
