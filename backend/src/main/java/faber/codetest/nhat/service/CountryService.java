package faber.codetest.nhat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import faber.codetest.nhat.entity.Country;
import faber.codetest.nhat.repository.CountryRepository;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class CountryService extends AbstractCrudService<Country, String> {
    private final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private HttpClientService httpClientService;

    private static final String COUNTRY_INIT_SRC = "https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json";

    @Override
    public JpaRepository<Country, String> getRepository() {
        return countryRepository;
    }

    /**
     * searchAvailableDepartureAirport like by code and name
     *
     * @param search
     * @return
     */
    public List<Country> searchByCodeAndName(String search) {
        if (StringUtils.isNotEmpty(search)) {
            return countryRepository.searchByCodeAndName("%" + search + "%");
        }
        return Collections.emptyList();
    }

    /**
     * init country list in database
     */
    @PostConstruct
    private void init() {
        LOGGER.info("Initializing Country MetaData...");
        initCountryList();
        LOGGER.info("Done Country MetaData...");
    }
    /**
     * read Country data from JSON file and import to MySQL DB
     */
    private void initCountryList() {
        // read content of remote json file over http connection
        String jsonString = httpClientService.send(COUNTRY_INIT_SRC, new HashMap<>(), String.class, HttpMethod.GET);
        JSONArray jsonArray = new JSONArray(jsonString);
        ObjectMapper objectMapper = new ObjectMapper();

        int length = jsonArray.length();
        // loop and parse json content to Country Data
        for (int i = 0 ; i < length; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            try {
                // save data to Database
                Country country = objectMapper.readValue(jsonObject.toString(), Country.class);
                save(country);
            } catch (JsonProcessingException e) {
                LOGGER.warn("Cannot import Country {}", jsonObject.toString());
            } catch (DataIntegrityViolationException ex) {
                LOGGER.warn("Country {} already exist", jsonObject.toString());
            }
        }
    }
}
