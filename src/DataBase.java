import java.sql.*;
import org.postgresql.util.PSQLException;
public class DataBase {
    String url;
    String user;
    String password;


    DataBase(String url,String user,String password) {
        this.url=url;
        this.user=user;
        this.password=password;
    }

    public void main(String[] args) throws Exception{



    }
//insert into groups(group_id,group_members,admin_id) values('4578849',ARRAY [['9458903','3434545']],'9458903')
    public void CreateGroup (container c) throws Exception{
        String group_id=c.receiver_id;
        String admin_id=c.sender_id;
        String adminssion_key=c.adminssion_id;
        String secert_key=c.secert_key;
        String ver=c.verification_id;
        System.out.println("in create group");
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            String query = String.format("insert into groups(group_id,member_id,ver,secert_key,adminssion_key,is_admin) values('%s','%s','%s','%s','%s',%c)", group_id, admin_id, ver, secert_key, adminssion_key, '1');
            ResultSet rs = st.executeQuery(query);
            st.close();
            con.close();
        }
        catch (PSQLException ex){
            System.out.println(ex);
            System.out.println(1);
        }
        catch (Exception ex){
            System.out.println(ex);
            System.out.println(4);
        }

    }
    //select admin_id from groups where group_id='4578849'
    public void AddGroupMember(container c) {
        String group_id=c.receiver_id;
        String member_id=c.sender_id;
        String adminssion_key=c.adminssion_id;
        String member_ver=c.verification_id;
        try {
            System.out.println("in add group member");
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            String query = String.format("select adminssion_key from groups where group_id='%s' and is_admin='1'", group_id);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String group_adminssion_id = rs.getString("adminssion_key");

            if (group_adminssion_id.equals(adminssion_key)) {
                System.out.println("in condition add member");
                query = String.format("insert into groups(group_id,member_id,ver) values('%s','%s','%s')", group_id, member_id, member_ver);
                st.executeQuery(query);

            }
            st.close();
            con.close();
        }
        catch (PSQLException ex){
            System.out.println(ex);
            System.out.println(1);
        }
        catch (Exception ex){
            System.out.println(ex);
            System.out.println(2);
        }


    }

    //select ver from groups where group_id=gi and member_id=mi
    //select member_id where group_id=gi;
    public void SendGroupMesseges(server SERVER,container c) throws Exception{
        String group_id=c.receiver_id;
        String member_id=c.sender_id;
        String ver=c.verification_id;
        try {
            System.out.println("in send group msg");
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            String query = String.format("select ver from groups where group_id='%s' and member_id='%s'", group_id, member_id);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if (ver.equals(rs.getString("ver"))) {
                System.out.println("in the condition send gmsg");
                query = String.format("select member_id from groups where group_id='%s'", group_id);
                rs = st.executeQuery(query);
                rs.next();
                while (rs.next()) {
                    HandleConnection.send(rs.getString("member_id"), c, SERVER);
                    System.out.println("here in the loop");
                }
            }
            st.close();
            con.close();
        }
        catch (Exception ex){
            System.out.println("in send group msg exception");
            System.out.println(ex);
        }
    }

    public void addMember(container c){
        String user_name=c.sender_id;
        String public_key=c.secert_key;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            String query=String.format("insert into users(user_name,member_primarykey,msges) values('%s','%s',ARRAY []::text[])",user_name,public_key);
            st.executeQuery(query);
            st.close();
            con.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }


    }

    public void AddMsg(container c){
        String msg=c.msg;
        int sno=c.serial_no;
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            String query=String.format("update users set msges=array_append(msges,'%s') where user_id=%d",msg,sno);
            st.executeQuery(query);

        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }


}
