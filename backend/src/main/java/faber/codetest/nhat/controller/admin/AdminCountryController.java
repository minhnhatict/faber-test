package faber.codetest.nhat.controller.admin;

import faber.codetest.nhat.entity.Country;
import faber.codetest.nhat.service.CountryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/country")
public class AdminCountryController {

    @Autowired
    private CountryService countryService;

    /**
     * searchAvailableDepartureAirport Country by Key
     *
     * @param searchKey
     * @return
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<Country>> search(@RequestParam(value = "searchKey", required = false) String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = "";
        }
        return ResponseEntity.ok(countryService.searchByCodeAndName(searchKey));
    }
}
