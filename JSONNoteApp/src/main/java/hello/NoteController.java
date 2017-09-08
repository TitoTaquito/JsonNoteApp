package main.java.hello;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    private static int counter = 0;
    private static HashMap <Integer, String> map = new HashMap<Integer, String>();
    private static ArrayList <Note> list = new ArrayList<Note>();

    @ResponseBody @RequestMapping(value = "/api/notes",headers = "Content-Type= application/json", method = RequestMethod.POST)
    public Note postNote(@RequestBody String body) {
    	counter ++;
        if(!body.contains(":")) {
    		System.err.println("Not a valid parameter.  Make sure your json object dosn't have spaces next to colons.\nex: '{\"body\":\"this example\"}' as opposed to '{\"body\" : \"this example\"}'");
    		return null;
    	}
    	body=body.substring(body.indexOf(':')+1).replace("}'", "").trim();
    	Note n= new Note(counter,body);
    	map.put(counter, body);
    	list.add(n);
        return n;
    }
    
    @RequestMapping(value = "/api/notes/{id}", method = RequestMethod.GET)
    public Note getSpecificNote(@PathVariable String id) {
        
    	int iId = Integer.parseInt(id);
    	if(map.containsKey(iId)) {
    		return new Note(iId, map.get(iId));
    	}
    	System.err.println("No such Note");
    	return null;
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.GET)
    public ArrayList<Note> getAllNotes(@RequestParam(value="query", required=false) String query) {
    	
    	if(query==null) {
    		return list;
    	}
    	ArrayList <Note> tempList = new ArrayList<Note>();
    	int size = map.size();
    	for(int i=1;i<=size;i++) {
    		if(map.get(i).contains(query)) {
    			tempList.add(new Note(i,map.get(i)));
    		}
    	}
    	return tempList;
    }
}
