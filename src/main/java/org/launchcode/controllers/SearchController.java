package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("check", "all");
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String SearchColumnValues(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobsByValue = JobData.findByValue(searchTerm);
            model.addAttribute("check", searchType);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("resultsCount", jobsByValue.size() + " Results(s)");
            model.addAttribute("jobs", jobsByValue);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobsByColumnAndValue = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("check", searchType);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("resultsCount", jobsByColumnAndValue.size() + " Results(s)");
            model.addAttribute("jobs", jobsByColumnAndValue);
            return "search";
        }
    }
}
