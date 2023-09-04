package br.com.UMADEJ.gui;

import br.com.UMADEJ.dao.JovemDAO;
import br.com.UMADEJ.model.Jovem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JovemDAOInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JovemDAO jovemDAO;
    private JTextField nomeField;
    private JTextField dataNascimentoField;
    private JTextField dadosDoJovemField;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnRemover;
    private JList<String> listaJovens;

    public JovemDAOInterface() {
        jovemDAO = new JovemDAO();

        setTitle("Cadastro de Jovens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do painel de formulário para entrada de nome, data de nascimento e dados do jovem
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
        panelFormulario.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panelFormulario.add(nomeField);
        panelFormulario.add(new JLabel("Data de Nascimento:"));
        dataNascimentoField = new JTextField();
        panelFormulario.add(dataNascimentoField);
        panelFormulario.add(new JLabel("Dados do Jovem:"));
        dadosDoJovemField = new JTextField();
        panelFormulario.add(dadosDoJovemField);

        // Criação do painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnRemover = new JButton("Remover");
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnRemover);

        // Criação do painel principal para agrupar os demais painéis
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBotoes, BorderLayout.CENTER);

        // Criação da lista de jovens com barra de rolagem
        listaJovens = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaJovens);

        // Adição dos painéis à janela principal
        add(panelPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(700, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Carrega a lista de jovens na interface gráfica
        loadJovens();

        // Configura os ouvintes de eventos para os botões
        configureButtonListeners();
    }

    private void loadJovens() {
        List<Jovem> jovens = jovemDAO.getJovens();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Jovem jovem : jovens) {
            model.addElement(jovem.getNome() + " - " + jovem.getDataNascimento() + " - " + jovem.getDadosDoJovem());
        }

        listaJovens.setModel(model);
    }

    private void configureButtonListeners() {
        // Ouvinte de evento para o botão "Adicionar"
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja adicionar o jovem?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    if (validateFields()) {
                        addJovem();
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de adicionar um jovem.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // Ouvinte de evento para o botão "Atualizar"
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateJovem();
            }
        });

        // Ouvinte de evento para o botão "Remover"
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeJovem();
            }
        });
    }

    private void addJovem() {
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        String dadosDoJovem = dadosDoJovemField.getText();

        Jovem jovem = new Jovem();
        jovem.setNome(nome);
        jovem.setDataNascimento(dataNascimento);
        jovem.setDadosDoJovem(dadosDoJovem);

        jovemDAO.save(jovem);

        loadJovens();
        clearFields();

        JOptionPane.showMessageDialog(this, "Jovem adicionado com sucesso!");
    }

    private void updateJovem() {
        int selectedIndex = listaJovens.getSelectedIndex();
        if (selectedIndex != -1) {
            String nome = nomeField.getText();
            String dataNascimento = dataNascimentoField.getText();
            String dadosDoJovem = dadosDoJovemField.getText();

            List<Jovem> jovens = jovemDAO.getJovens();
            if (!jovens.isEmpty()) {
                Jovem jovem = jovens.get(selectedIndex);
                jovem.setNome(nome);
                jovem.setDataNascimento(dataNascimento);
                jovem.setDadosDoJovem(dadosDoJovem);
                jovemDAO.update(jovem);
                loadJovens();
                clearFields();
                JOptionPane.showMessageDialog(this, "Jovem atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não há jovens para atualizar.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jovem para atualizar.");
        }
    }

    private void removeJovem() {
        int selectedIndex = listaJovens.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o jovem?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                List<Jovem> jovens = jovemDAO.getJovens();
                Jovem jovem = jovens.get(selectedIndex);
                jovemDAO.deleteByID(jovem.getId());
                loadJovens();
                clearFields();
                JOptionPane.showMessageDialog(this, "Jovem removido com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jovem para remover.");
        }
    }

    private void clearFields() {
        nomeField.setText("");
        dataNascimentoField.setText("");
        dadosDoJovemField.setText("");
    }

    private boolean validateFields() {
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        String dadosDoJovem = dadosDoJovemField.getText();
        return !nome.isEmpty() && !dataNascimento.isEmpty() && !dadosDoJovem.isEmpty();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JovemDAOInterface();
            }
        });
    }
}
