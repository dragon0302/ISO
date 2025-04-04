package DataManagement;

public class Acquisto {
    int ID_acquisto;
    boolean CodiceSconto;
    int quantita;
    int ID_Carello;
    int ID_Prodotto;

    public Acquisto(int Id_acquisto, boolean CodiceSconto, int quantita, int ID_Carello, int ID_Prodotto) {
        this.ID_acquisto = Id_acquisto;
        this.CodiceSconto = CodiceSconto;
        this.quantita = quantita;
        this.ID_Carello = ID_Carello;
        this.ID_Prodotto = ID_Prodotto;
    }

    public int getID_acquisto() {
        return ID_acquisto;
    }

    public void setID_acquisto(int ID_acquisto) {
        this.ID_acquisto = ID_acquisto;
    }

    public boolean isCodiceSconto() {
        return CodiceSconto;
    }

    public void setCodiceSconto(boolean codiceSconto) {
        CodiceSconto = codiceSconto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getID_Carello() {
        return ID_Carello;
    }

    public void setID_Carello(int ID_Carello) {
        this.ID_Carello = ID_Carello;
    }

    public int getID_Prodotto() {
        return ID_Prodotto;
    }

    public void setID_Prodotto(int ID_Prodotto) {
        this.ID_Prodotto = ID_Prodotto;
    }
}
