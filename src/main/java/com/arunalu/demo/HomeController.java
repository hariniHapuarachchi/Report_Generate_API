package com.arunalu.demo;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

        static ArrayList<SubUser> filters1 = new ArrayList<>();
//
//    static {
//        filters.put("hi",new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12)));
//        filters.put("mango",new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12)));
//        }
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String getString(@RequestBody ArrayList<SubUser> filters){


        if (filters != null) {
            for (int i=0;i<filters.size();i++){
                filters1.add(filters.get(i));
            }
        }
        return "SUCCESS";

    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ArrayList<SubUser> getString(){

        return filters1;

    }

}

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    static HashMap<String,String> filters = new HashMap<>();
//
//static {
//        filters.put("hi","Mango");
//        filters.put("hi","Banana");
//        }
//@RequestMapping(value = "/",method = RequestMethod.GET)
//public JSONObject getString(){
//        return new JSONObject(filters);
//        }
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public @ResponseBody Result search(@RequestParam HashMap<String,String> filters, HttpServletRequest request) {
//
//        return (Result) filters;
//    }
////    public Response getAllResults(){
////        HashMap<String, ArrayList<Integer>> map=new HashMap<String,ArrayList<Integer>>();
////
////        map.put("mango", new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12)));
////        map.put("banana", new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12)));
////
////        return Response.SC_OK.Entity(map).build();
////    }
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }



//    ObjectMapper objectMapper = new ObjectMapper();
//    String requestBody = null;
//    HttpResponse<String> response = null;
//        try {
//                requestBody = objectMapper
//                .writeValueAsString(filters);
//
//                HttpClient client = HttpClient.newHttpClient();
//                HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://httpbin.org/post"))
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//                response = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//                System.out.println(response.body());
//
//                } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                } catch (InterruptedException e) {
//                e.printStackTrace();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//
//                return (HashMap) response;