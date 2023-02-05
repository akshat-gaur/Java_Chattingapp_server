import java.util.ArrayList;

public class node {
    int index;
    node left;
    node right;

    node parent;
    ArrayList<StoreNode> data=new ArrayList<>();


    node(int index) {
        this.index = index;
        this.left = null;
        this.right = null;
        this.parent=null;


    }


    void add_child(node a){
        if (a.index>=this.index){
            if (this.right==null){
                this.right=a;
            }
        }
        else{
            if (this.left==null){
                this.left=a;
            }
        }
        a.parent=this;

    }

    void del_left(){
        this.left=null;
    }

    void  del_rigth(){
        this.right=null;
    }


}

