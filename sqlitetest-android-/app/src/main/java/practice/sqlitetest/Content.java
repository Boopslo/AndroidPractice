package practice.sqlitetest;

/**
 * Created by Oslo on 7/21/15.
 */
public class Content {

    private long id;
    private String content;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    @Override
    public String toString(){
        return content;
    }

}
