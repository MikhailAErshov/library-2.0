package com.example.library20.newspaper;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.example.library20.Application.newspapers;
import static com.example.library20.Application.writeBookData;
import static com.example.library20.Application.writeNewspaperData;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newspaper")
public class NewspaperController {

    Faker faker = new Faker();

    @PostMapping(path = "/add")
    public NewspaperDto addRandomNewspaper() {
        final NewspaperDto newNp =
                new NewspaperDto(faker.book().title(), faker.date().past(20, TimeUnit.DAYS).toInstant());
        newspapers.add(newNp);
        writeNewspaperData(newspapers);
        return newNp;
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewspaper(@RequestParam String name) {
        final List<NewspaperDto> npToDelete =
                newspapers.stream().filter(np -> Objects.equals(np.getName(), name)).toList();
        npToDelete.forEach(b -> newspapers.remove(b));
        writeNewspaperData(newspapers);
    }
}
