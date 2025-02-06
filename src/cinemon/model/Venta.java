package cinemon.model;

public class Venta {
    private int id;
    private Butaca butaca;
    private Session session;
    public Venta(int id, Butaca butaca, Session session) {
        this.id = id;
        this.butaca = butaca;
        this.session = session;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Butaca getButaca() {
        return butaca;
    }
    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Id: " + id + "\n");
        System.out.println(session);
        System.out.println(butaca);
        return sb.toString();
    }
    
}
