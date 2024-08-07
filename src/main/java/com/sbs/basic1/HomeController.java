package com.sbs.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private int cnt;
    private List<Person> people; //전역변수로 끄집어내야함
    public HomeController() {
        cnt = -1;
        people = new ArrayList<>();
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showHome() {
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showHome2() {
        return "환영합니다!";
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }
    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showDouble(){
        return Math.PI;
    }
    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showIntList() {
        List<Integer> list = new ArrayList<>(){{
            add(10);

            add(20);

            add(30);
        }};
        return list;
    }
    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap(){
        Map<String,Object> map = new LinkedHashMap<>(){{
            put("id",1);
            put("speed",170);
            put("Name", "아반떼");
            put("relatedId",new ArrayList<>(){{
                add(1);
                add(2);
                add(3);
            }});
        }};
        return map;
    }
    class Car {
        public Car(int id, int speed, String name,int relatedIds) {
            this.id = id;
            this.speed = speed;
            this.name = name;
            this.relatedIds = relatedIds;
        }

        private  final int id;
        private final int speed;
        private final String name;
        private final int relatedIds;
    }
    @GetMapping("/home/returnCar2")
    @ResponseBody
    public CarV2 showReturnCar2(){
        CarV2 car = new CarV2(1,100,"람보르",new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }});
        car.setName(car.getName()+"Version2");
        return car;

    }
    @AllArgsConstructor
    @Getter
    class CarV2{
        private  final int id;
        private final int speed;
        @Setter
        private String name;
        private final List<Integer> relatedIds;

        public void setName(String name) {
            this.name = name;
        }
    }
    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String,Object>> showReturnCarMapList() {
        Map<String, Object> carmap1 = new LinkedHashMap<>() {
            {
                put("id", 1);
                put("speed", 170);
                put("Name", "아반떼");
                put("relatedId", new ArrayList<>() {{
                    add(2);
                    add(3);
                    add(4);
                }});
            }};
        Map<String, Object> carmap2 = new LinkedHashMap<>() {
            {
                put("id", 2);
                put("speed", 180);
                put("Name", "산타페");
                put("relatedId", new ArrayList<>() {{
                    add(5);
                    add(6);
                    add(7);
                }});
            }};
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(carmap1);
        list.add(carmap2);
        return list;
    }

    @GetMapping("/home/returnCarMapList1")
    @ResponseBody
    public List<CarV2> showReturnCarMapList1() {
        CarV2 car1 = new CarV2(1,100,"아반떼1",new ArrayList<>(){{
                    add(2);
                    add(3);
                    add(4);
                }});

        CarV2 car2 = new CarV2(2,200,"산타페1", new ArrayList<>(){{
                    add(5);
                    add(6);
                    add(7);
                }});
        List<CarV2> list = new ArrayList<>();
        list.add(car1);
        list.add(car2);
        return list;
    }
    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name,int age){
        Person p = new Person(name,age);
        System.out.println(p);
        people.add(p); //리스트의 데이터를 보존하기 위해서
        return "%d번 사람추가되었습니다.".formatted(p.getId());
    }
    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople(){
        return people;
    }
    @AllArgsConstructor
    @Getter //getId받기 위해서
    @ToString //결과 출력한거 보여줌 ex) HomeController.Person(id=1, name=dru, age=87)
    class Person {
        private static int lastId=0;
        private int id;
        private final String name;
        private final int age;


        public Person(String name, int age) {
            this.id = ++lastId; //사람추가될때마다 증가
            this.name=name;
            this.age=age;
        }
    }

}
