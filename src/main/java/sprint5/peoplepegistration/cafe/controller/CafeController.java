package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sprint5.peoplepegistration.cafe.service.CafeService;

@RestController
@RequestMapping("/api/v1/menu")
@AllArgsConstructor
public class CafeController {

    private final CafeService cafeService;
}
