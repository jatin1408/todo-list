package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {
    private final TodoItemService service;
    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }
    @ModelAttribute
    public TodoData todoData(){
        return service.getData();
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItems(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem){
        log.info("toData from form = {}",todoItem);
        if(todoItem.getId()==0){
            service.addItem(todoItem);
        }
        else {
            service.updateItem(todoItem);
        }

        return "redirect:/" + Mappings.items;
    }
    @GetMapping(Mappings.items)
    public String items(){
        return ViewNames.ITEMS_LIST;
    }
    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false,defaultValue = "-1") int id, Model model){
        TodoItem todoItem=service.getItem(id);
        if(todoItem==null) {
            log.info("I am getting called");
            todoItem = new TodoItem("", "", LocalDate.now());
        }
        model.addAttribute(AttributeNames.TODO_ITEM,todoItem);

        return ViewNames.ADD_ITEM;
    }
    @GetMapping(Mappings.DELETE)
    public String deleteItem(@RequestParam int id){
        service.removeItem(id);
        return "redirect:/" + Mappings.items;
    }
    @GetMapping(Mappings.VIEW)
    public String viewItem(@RequestParam int id,Model model){
        TodoItem todo=service.getItem(id);
        model.addAttribute(AttributeNames.TODO_ITEM,todo);
        return ViewNames.VIEW_ITEM;

    }

}
