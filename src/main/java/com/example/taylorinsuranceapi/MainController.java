package com.example.taylorinsuranceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")   //versioning the API
public class MainController {

    //TODO use this or not?
    public static final String VERSION_1 = "/v1";
    public static final String CUSTOMER = "/customers";
    public static final String HOME = "/homes";
    public static final String AUTO = "/autos";
    public static final String AUTOQUOTE = "/autoquotes";
    public static final String HOMEQUOTE = "/homequotes";
    public static final String AUTOPOLICY = "/autopolicies";
    public static final String HOMEPOLICY = "/homepolicies";


    @Autowired  //wires the customerRepository
    private CustomerRepository customerRepository;

    @Autowired  //wires the homeRepository
    private HomeRepository homeRepository;

    @Autowired //wires the autoRepository
    private AutoRepository autoRepository;

    @Autowired //wires the homeQuoteRepository
    private HomeQuoteRepository homeQuoteRepository;

    @Autowired //wires the autoQuoteRepository
    private AutoQuoteRepository autoQuoteRepository;

    @Autowired //wires the homePolicyRepository
    private HomePolicyRepository homePolicyRepository;

    @Autowired //wires the autoPolicyRepository
    private AutoPolicyRepository autoPolicyRepository;

    @GetMapping(CUSTOMER + "/{id}")
    public @ResponseBody
    Optional<Customer> getCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id);
    }

    @GetMapping(path = CUSTOMER)
    public @ResponseBody
    Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping(path = CUSTOMER)
    public @ResponseBody
    String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam("dateOfBirth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth, @RequestParam String password){
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPassword(password);
        customerRepository.save(customer);
        return "Customer Saved";
    }

    @GetMapping(CUSTOMER + "/{id}" + HOME)
    public @ResponseBody
    Iterable<Home> getHomesByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getHomes();
    }

    @GetMapping(path = CUSTOMER + HOME)
    public @ResponseBody
    Iterable<Home> getAllHomes(){
        return homeRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + HOME)
    public @ResponseBody
    String addNewHome(@PathVariable Integer id,
                      @RequestParam int value, @RequestParam int streetNumber, @RequestParam String streetName,
                      @RequestParam String city, @RequestParam String postalcode,
                      @RequestParam("dateBuilt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBuilt,
                      @RequestParam Home.HeatingType heatingType, @RequestParam Home.Location location){
        Home home = new Home();
        home.setStreetNumber(streetNumber);
        home.setStreetName(streetName);
        home.setCity(city);
        home.setPostalcode(postalcode);
        home.setValue(value);
        home.setDateBuilt(dateBuilt);
        home.setHeatingType(heatingType);
        home.setLocation(location);

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            home.setCustomer(customer.get());
            homeRepository.save(home);
            return "Home Saved";
        } else {
            return "No customer found";
        }
    }

    @GetMapping(CUSTOMER + "/{id}" + AUTO)
    public @ResponseBody
    Iterable<Auto> getAutosByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getAutos();
    }

    @GetMapping(path = CUSTOMER + AUTO)
    public @ResponseBody
    Iterable<Auto> getAllAutos(){
        return autoRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + AUTO)
    public @ResponseBody
    String addNewAuto(@PathVariable Integer id,
                      @RequestParam String make, @RequestParam String model,
                      @RequestParam("dateMade") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMade){
        Auto auto = new Auto();
        auto.setMake(make);
        auto.setModel(model);
        auto.setDateMade(dateMade);

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            auto.setCustomer(customer.get());
            autoRepository.save(auto);
            return "Auto Saved";
        } else {
            return "No customer found";
        }
    }

    @GetMapping(HOME + "/{id}" + HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getHomeQuoteWithId(@PathVariable Integer id){
        return homeRepository.findById(id).get().getHomeQuotes();
    }

    @GetMapping(path = HOME + HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getAllHomeQuotes(){
        return homeQuoteRepository.findAll();
    }

    @PostMapping(HOME + "/{id}" + HOMEQUOTE)
    public @ResponseBody
    String addNewHomeQuote(@PathVariable Integer id,
                           @RequestParam double homeQuotePremium,
                           @RequestParam("homeQuoteStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate homeQuoteStartDate,
                           @RequestParam("homeQuoteEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate homeQuoteEndDate) {
        HomeQuote homeQuote = new HomeQuote();
        homeQuote.setHomeQuotePremium(homeQuotePremium);
        homeQuote.setHomeQuoteStartDate(homeQuoteStartDate);
        homeQuote.setHomeQuoteEndDate(homeQuoteEndDate);

        Optional<Home> home = homeRepository.findById(id);

        if (home.isPresent()) {
            homeQuote.setHome(home.get());
            homeQuoteRepository.save(homeQuote);
            return "HomeQuote saved";
        }
        else {
            return "Home not found";
        }
    }

    @GetMapping(AUTO + "/{id}" + AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAutoQuoteWithId(@PathVariable Integer id){
        return autoRepository.findById(id).get().getAutoQuotes();
    }

    @GetMapping(path = AUTO + AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAllAutoQuotes(){
        return autoQuoteRepository.findAll();
    }

    @PostMapping(AUTO + "/{id}" + AUTOQUOTE)
    public @ResponseBody
    String addNewAutoQuote(@PathVariable Integer id,
                           @RequestParam double autoQuotePremium,
                           @RequestParam("autoQuoteStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate autoQuoteStartDate,
                           @RequestParam("autoQuoteEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate autoQuoteEndDate) {
        AutoQuote autoQuote = new AutoQuote();
        autoQuote.setAutoQuotePremium(autoQuotePremium);
        autoQuote.setAutoQuoteStartDate(autoQuoteStartDate);
        autoQuote.setAutoQuoteEndDate(autoQuoteEndDate);

        Optional<Auto> auto = autoRepository.findById(id);

        if (auto.isPresent()) {
            autoQuote.setAuto(auto.get());
            autoQuoteRepository.save(autoQuote);
            return "AutoQuote saved";
        } else {
            return "Auto not found";
        }
    }

    @GetMapping(HOME + "/{id}" + HOMEPOLICY)
    public @ResponseBody
    Optional<HomePolicy> getHomePolicyWithId(@PathVariable Integer id){
        return Optional.ofNullable(homeRepository.findById(id).get().getHomePolicy());
    }

    @GetMapping(path = HOME + HOMEPOLICY)
    public @ResponseBody
    Iterable<HomePolicy> getAllHomePolicies(){
        return homePolicyRepository.findAll();
    }

    @PostMapping( HOME + "/{id}" + HOMEPOLICY)
    public @ResponseBody
    String addNewHomePolicy(@PathVariable Integer id,
                            @RequestParam double homePolicyPremium,
                            @RequestParam("homePolicyStartDate")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate homePolicyStartDate,
                            @RequestParam("homePolicyEndDate")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate homePolicyEndDate) {
        HomePolicy homePolicy = new HomePolicy();
        homePolicy.setHomePolicyPremium(homePolicyPremium);
        homePolicy.setHomePolicyStartDate(homePolicyStartDate);
        homePolicy.setHomePolicyEndDate(homePolicyEndDate);

        Optional<Home> home = homeRepository.findById(id);

        if (home.isPresent()) {
            homePolicy.setHome(home.get());
            // TODO : ask Josh
            home.get().setHomePolicy(homePolicy);
            homePolicyRepository.save(homePolicy);
            return "Home Policy saved";
        } else {
            return "Home Quote not found";
        }
    }


    @GetMapping(AUTO + "/{id}" + AUTOPOLICY)
    public @ResponseBody
    Optional<AutoPolicy> getAutoPolicyWithId(@PathVariable Integer id){
        return Optional.ofNullable(autoRepository.findById(id).get().getAutoPolicy());
    }

    @GetMapping(path = AUTO + AUTOPOLICY)
    public @ResponseBody
    Iterable<AutoPolicy> getAllAutoPolicies(){
        return autoPolicyRepository.findAll();
    }

    @PostMapping(AUTO + "/{id}" + AUTOPOLICY)
    public @ResponseBody
    String addNewAutoPolicy(@PathVariable Integer id,
                           @RequestParam double autoPolicyPremium,
                            @RequestParam("autoPolicyStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate autoPolicyStartDate,
                            @RequestParam("autoPolicyEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate autoPolicyEndDate) {
        AutoPolicy autoPolicy = new AutoPolicy();
        autoPolicy.setAutoPolicyPremium(autoPolicyPremium);
        autoPolicy.setAutoPolicyStartDate(autoPolicyStartDate);
        autoPolicy.setAutoPolicyEndDate(autoPolicyEndDate);

        Optional<Auto> auto = autoRepository.findById(id);

        if (auto.isPresent()) {
            autoPolicy.setAuto(auto.get());
            auto.get().setAutoPolicy(autoPolicy);
            autoPolicyRepository.save(autoPolicy);
            return "Auto Policy saved";
        } else {
            return "Auto Quote not found";
        }
    }
}
