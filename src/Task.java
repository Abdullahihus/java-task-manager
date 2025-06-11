public class Task{

    private String title; 
    private boolean isDone;

    public Task(String title){
        this.title = title; 
        this.isDone = isDone;
    }

    public String getTitle(){
        return title;
    }

    public boolean getisDone(){
        return isDone;
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override 
    public String toString(){
        return (isDone ? "[x] " : "[ ] ") + title;
    } 
}