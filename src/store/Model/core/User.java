/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.core;

public abstract class User {
    private final String username;
    private final String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    @Override
    public String toString(){
        return "username:" + username +
                "email:" + email;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){return true;}
        if(obj == null || this.getClass() != obj.getClass()){return false;}
        User other = (User) obj;
        return this.username.equals(other.username);
    }

}
