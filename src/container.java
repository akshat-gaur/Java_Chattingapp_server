import java.io.FileReader;
import java.io.Serializable;

public class container implements Serializable {
    String sender_id;
    String receiver_id;
    String verification_id;
    boolean is_str=true;
    String msg;
    char action;
    byte [] file;

    String [] VerArray;
    int position;

    String adminssion_id;

    String secert_key;

    int serial_no;



    container(String receiver_id,String Verification_id,String msg,char action,int position){
        this.receiver_id=receiver_id;
        this.verification_id=Verification_id;
        this.action=action;
        this.msg=msg;


    }

    container(String receiver_id,String Verification_id,String msg,char action,byte [] file,int position){
        this.receiver_id=receiver_id;
        this.verification_id=Verification_id;
        this.msg=msg;
        this.file=file;
        this.action=action;
        this.is_str=false;
        this.position=position;

    }

    container(String sender_id,char action,String [] VerArray){
        this.sender_id=sender_id;
        this.action=action;
        this.VerArray=VerArray;

    }

    container(String sender_id,String verification_id,String msg,String group_id,char action){
        this.sender_id=sender_id;
        this.receiver_id=group_id;
        this.msg=msg;
        this.verification_id=verification_id;
        this.action=action;

    }

    container(String sender_id,String verification_id,String msg,String group_id,byte [] file,char action){
        this.sender_id=sender_id;
        this.receiver_id=group_id;
        this.msg=msg;
        this.verification_id=verification_id;
        this.file=file;
        this.action=action;
    }

    container(char action,String sender_id,String verification_id,String group_id,String adminssion_id){
        this.sender_id=sender_id;
        this.receiver_id=group_id;
        this.adminssion_id=adminssion_id;
        this.verification_id=verification_id;
        this.action=action;
    }


    container(char action,String sender_id,String verification_id,String group_id,String adminssion_id,String secert_key){
        this.sender_id=sender_id;
        this.receiver_id=group_id;
        this.adminssion_id=adminssion_id;
        this.verification_id=verification_id;
        this.action=action;
        this.secert_key=secert_key;
    }

    container(char action,String user_public_key,String user_name){
        this.action=action;
        this.sender_id=user_name;
        this.secert_key=user_public_key;
    }

    container(char action,int Sno,String msg){
        this.action=action;
        this.msg=msg;
        this.serial_no=Sno;
    }



}
