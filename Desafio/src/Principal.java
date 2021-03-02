import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Principal {
    public static void main(String[] args) throws IOException {
        ArrayList <Item> itens;
        ArrayList <String> emails;
        itens=carregarItens();
        emails=carregarEmails();
        HashMap<String,Integer> resultado = calcula(itens,emails);
        System.out.println(resultado);
    }

    private static HashMap<String,Integer> calcula(ArrayList<Item> itens, ArrayList<String> emails){
        HashMap<String,Integer> resultado = new HashMap<>();
        if (emails.size()!=0){
            Integer valor_total = 0, valor_restante = 0;
            Double valor_parcial = 0.0;
            for (Item i: itens){
                valor_total+= (i.getPreco()*i.getQuantidade());
            }
            valor_restante=valor_total;
            for (int i=0;i<emails.size();i++){
                if (i == emails.size()-1) {
                    resultado.put(emails.get(i), valor_restante);
                }else {
                    valor_parcial=Math.floor(valor_restante.doubleValue()/(emails.size()-i));
                    valor_restante-=valor_parcial.intValue();
                    resultado.put(emails.get(i), valor_parcial.intValue());
                }
            }
        }else{
            System.out.println("Lista de emails vazia!");
        }
        if(itens.size()==0)
            System.out.println("Lista de itens Vazia!");
        return resultado;
    }

    private static ArrayList<Item> carregarItens() throws IOException {
        String path = "Arquivos/Itens.txt";
        ArrayList<Item> itens = new ArrayList();
        ArrayList<String> linha = LeitorArq.leitor(path);
        for (String s : linha) {
            String [] info = s.split(",");
            Item i = new Item(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]));
            itens.add(i);
        }
        return itens;
    }

    private static ArrayList<String> carregarEmails() throws IOException {
        String path = "Arquivos/Emails.txt";
        ArrayList<String> emails = new ArrayList();
        ArrayList<String> linha = LeitorArq.leitor(path);
        for (String s : linha) {
            emails.add(s);
        }
        return emails;
    }
}
