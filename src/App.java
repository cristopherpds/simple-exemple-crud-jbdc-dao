import java.util.Date;
import dao.ContatoDAO;
import models.Contato;

public class App {
    public static void main(String[] args) throws Exception {

        //Save contato
        Contato contato = new Contato();
        contato.setNome("Malena Paiva");
        contato.setIdade(22);
        contato.setDataCadastro(new Date());
        
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.save(contato);

        //Update contato
        Contato c1 = new Contato();
        c1.setNome("Malena da Silva");
        c1.setIdade(23);
        c1.setDataCadastro(new Date());
        c1.setId(2);

        contatoDAO.updateContato(c1);

        //Delete contato
        contatoDAO.deleteByID(3);


        //Visualization
        for (Contato c : contatoDAO.findContatos()) {
            System.out.println("Contato: " + c.getNome());
        }
    }
}
