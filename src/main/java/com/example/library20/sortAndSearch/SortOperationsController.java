package com.example.library20.sortAndSearch;

import com.example.library20.newspaper.NewspaperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import static com.example.library20.Application.newspapers;

@RequestMapping("/sort")
@RestController
@RequiredArgsConstructor
public class SortOperationsController {

    private final SortOperations operationsHandler;


    @GetMapping(path = "/byDate")
    public List<NewspaperDto> getNewspapersByDate() {
        return operationsHandler.sortNewspapersByDate(newspapers);
    }

    @GetMapping(path = "/fromDate")
    public List<NewspaperDto> getNewspapersFromDate(@RequestParam String date) {
        final Instant instantDate = Instant.parse(date);
        return operationsHandler.sortNewspapersFromDate(instantDate);
    }

}
