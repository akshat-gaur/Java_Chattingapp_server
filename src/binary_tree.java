



public class binary_tree {


    node root=new node(0);



    void insert(node x) {
        node a = this.root;
        node b = root;
        int is_broken = 0;
        while (a != null) {
            b = a;
            if (x.index == a.index) {
                StoreNode dy=x.data.get(0);
                a.data.add(dy);
                is_broken = 1;
                break;
            }
            if (x.index >= a.index) {
                a = a.right;
            } else {
                a = a.left;
            }
        }
        if (is_broken == 0) {
            b.add_child(x);
        }

    }


    node search(int x){
        node a=this.root;
        while (a!=null){
            if (a.index == x){
                return a;
            }
            if (x>=a.index){
                a=a.right;
            }
            else{
                a=a.left;
            }
        }
        return null;
    }

    static node find_min(node x){
        node a=x;
        node b=a;
        while (a!=null){
            b=a;
            a=a.left;
        }
        return b;
    }


    static node find_max(node x){
        node a=x;
        node b=a;
        while (a!=null){
            b=a;
            a=a.right;
        }
        return b;

    }

    static node succesor(node x) {
        if (x.right == null){
            node a=x.parent;
            node b=a;
            while (a!=null) {
                b=a;
                if (a.index >= x.index) {
                    return a;
                }
                a = a.parent;
            }
            return null;
        }
        else{
            return find_min(x.right);
        }
    }

     static node predessor(node x){
        if (x.left == null){
            node a=x.parent;
            node b=a;
            while (a!=null) {
                b=a;
                if (a.index < x.index) {
                    if(a.index==0){
                        return null;
                    }
                    return a;
                }
                a = a.parent;
            }
            return b;
        }
        else{
            return find_max(x.left);
        }

    }


    void delete(node x){
        if (x.left==null && x.right==null){
            node p=x.parent;
            if (p!=null){
                if (x.index>=p.index){
                    p.right=null;
                }
                else{
                    p.left=null;
                }
            }
        }
        if (x.left!=null && x.right!=null){
            node s=succesor(x);
            int i=s.index;
            x.index=i;
            delete(s);
        } else if (x.left!=null || x.right!=null) {
            node f;
            if (x.left!=null){
                 f=x.left;
            }
            else {
                 f=x.right;
            }
            node p=x.parent;
            if (p!=null){
                if (x.index>=p.index){
                    p.right=f;
                }
                else {
                    p.left=f;
                }
            }

        }


    }

    void delete(node x,StoreNode s){
        if (x.data.size()==0){
            delete(x);
        }
        else{
            for (int i=0;i<x.data.size();i++){
                if (s==x.data.get(i)){
                    x.data.remove(i);
                }
            }
        }
        if(x.data.size()==0){
            delete(x);
        }
    }


}


