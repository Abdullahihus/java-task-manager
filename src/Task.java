public class Task{

    private String title; 
    private boolean isDone;
    private String dueDate; 
    private String priority;

    public Task(String title, String dueDate, String priority){
        this.title = title; 
        this.dueDate = dueDate;    
        this.priority = priority;
        this.isDone = false;
    }

    public Task(String title){
        this(title, "No due date", "Medium");
    }

    public String getTitle(){
        return title;
    }

    public boolean isDone(){
        return isDone;
    }

    public void markDone(){
        this.isDone = true;
    }

    public String getDueDate(){
        return dueDate;
    }
    public String getPriority(){
        return priority;
    }

    @Override 
    public String toString(){
        return (isDone ? "[x] " : "[ ] ") + title +  " (Due: " + dueDate + ", Priority: " + priority + ") ";
    } 
}