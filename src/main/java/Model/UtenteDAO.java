package Model;

import java.sql.SQLException;

public interface UtenteDAO {
    public void DoSave(Utente utente) throws SQLException;
    public boolean CFEsistente(String CF) throws SQLException;
    public boolean UtenteEsistente(String nomeUtente) throws SQLException;
    public boolean isAmministratore(String CF) throws SQLException;
    public boolean isUtente(String nomeutente, String password) throws SQLException;
    public String getCF(String nomeUtente) throws SQLException;
    public Utente getUtente(String NomeUtente) throws SQLException;
    public boolean EmailEsistente(String email) throws SQLException;
    public void editDataNascita(Utente utente,java.sql.Date new_dn) throws SQLException;
    public void editAdminPrivileges(Utente utente,boolean new_admin) throws SQLException;
    public void editSesso(Utente utente,String new_sesso) throws SQLException;
    public void editCognome(Utente utente,String new_cognome) throws SQLException;
    public void editNome (Utente utente,String new_nome) throws SQLException;
    public void editPassword(Utente utente,String new_password) throws SQLException;
    public void editNomeUtente (Utente utente,String new_nomeutente) throws SQLException;
    public void editCF (Utente utente,String new_cf) throws SQLException;
    public void remouveUtente (Utente utente) throws SQLException;

}
