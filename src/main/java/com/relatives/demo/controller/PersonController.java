package com.relatives.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 */
@RestController
@RequestMapping("/person")
public class PersonController {
//    @Autowired
//    CsvImportRepository csv;
//    @RequestMapping("/csv")
//    public void a(){
//    //    csv.addNode();
//      //  csv.addRelation(FileName);
//    }
//    private final PersonService personService;
//
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }
//    @Autowired
//    PersonRepository personRepository;
//
//    @Autowired
//    MovieRepository movieRepository;
//
//    @GetMapping("/findByfirstName")
//    public Person findByfirstName(@RequestParam String name) {
//        return personService.findByfirstName(name);
//    }
//
//    @GetMapping("/likeName")
//    public Collection<Person> findByNameLike(@RequestParam String name) {
//        return personService.findByNameLike(name);
//    }
//
//    @GetMapping("/all")
//    public Iterable<Person> findAll() {
//        return personRepository.findAll();
//    }




//    @GetMapping("/save")
//    @Transactional
//    public String  Create() throws Exception{
//        Person person =new Person("RUAN", "SHUQI",158);
//        Person result = personRepository.save(person);
//        System.out.println(result);
//        if(result!=null){
//            return "节点创建成功";
//        }
//        return "节点创建失败！";
//    }
//
//    @GetMapping("/clean")
//    public Iterable<Person> clean() {
//        //clean work
//        Iterable<Person> persons = personRepository.findAll();
//        for (Person person : persons) {
//            person.setAMovies(null);
//            personRepository.save(person);
//        }
//        personRepository.deleteAll();
//        movieRepository.deleteAll();
//        return personRepository.findAll();
//    }
//
//    @GetMapping("/init")
//    public Iterable<Person> init() {
//        //clean work
//        Iterable<Person> persons = personRepository.findAll();
//        for (Person person : persons) {
//            person.setAMovies(null);
//            personRepository.save(person);
//        }
//        personRepository.deleteAll();
//        movieRepository.deleteAll();
//
//        //以下数据纯属虚构
//        //参演了 《建国大业A》
//        Person clPerson  = new Person("ChengLongP", "Jack", 175);
//        //参演了《长城B》
//        Person jtPerson = new Person("JingTianP", "JT", 170);
//        //参演了《长城B》 《建国大业A》
//        Person ldhPerson = new Person("LiuDeHuaP", "DeHua", 180);
//        //导演了《长城B》
//        Person zymPerson  = new Person("ZhangYiMouP", "YiMou", 176);
//
//        personRepository.save(clPerson);
//        personRepository.save(jtPerson);
//        personRepository.save(ldhPerson);
//        personRepository.save(zymPerson);
//
//        Movie jgMovie = new Movie("建国大业A" , "history", 9000);
//        movieRepository.save(jgMovie);
//        Movie clMovie = new Movie("长城B", "science fiction", 5000);
//        movieRepository.save(clMovie);
//
//        clPerson.addActMovie(jgMovie);
//
//        jtPerson.addActMovie(clMovie);
//
//        ldhPerson.addActMovie(jgMovie);
//        ldhPerson.addActMovie(clMovie);
//
//        zymPerson.addDirectMovie(clMovie);
//
//        personRepository.save(clPerson);
//        personRepository.save(jtPerson);
//        personRepository.save(ldhPerson);
//        personRepository.save(zymPerson);
//
//        return personRepository.findAll();
//    }

}