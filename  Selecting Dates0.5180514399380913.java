·       All the functionalities should be working as per the user story.
·       Ability to select the dates from the calendar.
·       Ability to select the month from the drop down menu.
·       Date should be displayed as per selection done by the user.


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Controller
public class DatePickerController {
		
    @Autowired
    private DatePickerService datePickerService;
		
    @GetMapping("/selectDate")
    public String selectDate(Model model){
        model.addAttribute("dateList", datePickerService.getDates());
        return "selectDate";
    }
	
    @PostMapping("/selectDate")
    public String selectDate(@RequestParam("date") LocalDate date, Model model){
        model.addAttribute("selectedDate", date);
        return "selectDate";
    }
}

@Service
public class DatePickerService {
	
    @Autowired
    private DatePickerRepository datePickerRepository;
	
    public List<LocalDate> getDates(){
        return datePickerRepository.getDates();
    }
}

@Repository
public class DatePickerRepository {
	
    public List<LocalDate> getDates(){
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.now());
        dates.add(LocalDate.now().plusDays(1));
        dates.add(LocalDate.now().plusDays(7));
        return dates;
    }
}