package br.com.vendadireta.dao;

import br.com.vendadireta.entidade.ItemVenda;
import br.com.vendadireta.entidade.Produto;
import br.com.vendadireta.entidade.Venda;
import br.com.vendadireta.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 28/04/2016 - Classe: Venda
 */
public class VendaDAO extends GenericDAO<Venda> {

    public void salvar(Venda venda, List<ItemVenda> itensVenda) {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(venda);

            for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
                ItemVenda itemVenda = itensVenda.get(posicao);
                itemVenda.setVenda(venda);
                sessao.save(itemVenda);

                Produto produto = itemVenda.getProduto();

                int quantidade = produto.getEstoque() - itemVenda.getQuantidade();

                if (quantidade >= 0) {
                    produto.setEstoque(new Short(quantidade + ""));
                    sessao.update(produto);
                } else {
                    throw new RuntimeException("Quantidade insuficiente em estoque");
                }
            }

            transacao.commit();
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
