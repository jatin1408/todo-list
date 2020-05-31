package academy.learnprogramming.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TodoData {
    private static int idValue=1;
    private final List<TodoItem> items=new ArrayList<>();

    public TodoData() {
        addItem(new TodoItem("first","first details", LocalDate.now()));
        addItem(new TodoItem("second","second details", LocalDate.now()));
        addItem(new TodoItem("third","third details", LocalDate.now()));
        addItem(new TodoItem("fourth","fourth details", LocalDate.now()));


    }
    public List<TodoItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    public void addItem(@NonNull TodoItem todoItem){
        todoItem.setId(idValue);
        items.add(todoItem);
        idValue++;
    }
    public void removeItem(int id){
        ListIterator<TodoItem> iterator=items.listIterator();
        while (iterator.hasNext()){
            TodoItem item=iterator.next();
            if(item.getId()==id){
                iterator.remove();
                break;
            }
        }
    }
    public TodoItem getItem(int id){
        for(TodoItem item:items){
            if(item.getId()==id){
                return item;

            }
        }
        return null;
    }
    public void updateItem(@NonNull TodoItem todoItem){
        ListIterator<TodoItem> listIterator=items.listIterator();
        while (listIterator.hasNext()){
            TodoItem todoItem1=listIterator.next();
            if(todoItem1.equals(todoItem)){
                listIterator.set(todoItem);
                break;

            }
        }
    }
}
