package br.com.UMADEJ.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import br.com.UMADEJ.dao.JovemDAO;
import br.com.UMADEJ.model.Jovem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class JovemGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JovemDAO jovemDAO;
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextArea dadosDoJovemArea;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JTable jovensTable;
    private DefaultTableModel tableModel;

    public JovemGUI() {
        jovemDAO = new JovemDAO();

        setTitle("Cadastro de Jovens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel(new GridLayout(4, 2));
        formularioPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formularioPanel.add(nomeField);
        formularioPanel.add(new JLabel("Idade:"));
        idadeField = new JTextField();
        formularioPanel.add(idadeField);
        formularioPanel.add(new JLabel("Dados do Jovem:"));
        dadosDoJovemArea = new JTextArea();
        formularioPanel.add(new JScrollPane(dadosDoJovemArea));

        JPanel botoesPanel = new JPanel(new FlowLayout());
        adicionarButton = new JButton("Adicionar");
        atualizarButton = new JButton("Atualizar");
        excluirButton = new JButton("Excluir");
        botoesPanel.add(adicionarButton);
        botoesPanel.add(atualizarButton);
        botoesPanel.add(excluirButton);

        JPanel formularioBotoesPanel = new JPanel(new BorderLayout());
        formularioBotoesPanel.add(formularioPanel, BorderLayout.CENTER);
        formularioBotoesPanel.add(botoesPanel, BorderLayout.SOUTH);

        // Criar um modelo de tabela com as colunas desejadas
        tableModel = new DefaultTableModel(new String[] { "Nome", "Idade", "Dados do Jovem" }, 0);

        // Criar a tabela com o modelo de tabela
        jovensTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(jovensTable);

        add(formularioBotoesPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        carregarJovens();

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarJovem();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarJovem();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirJovem();
            }
        });

        jovensTable.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void carregarJovens() {
        try {
            List<Jovem> jovens = jovemDAO.listarTodos();

            // Limpar a tabela antes de adicionar os novos dados
            tableModel.setRowCount(0);

            for (Jovem jovem : jovens) {
                // Adicionar uma nova linha à tabela com os dados do jovem
                tableModel.addRow(new Object[] { jovem.getNome(), jovem.getIdade(), jovem.getDadosDoJovem() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar a lista de jovens.");
        }
    }

    private void adicionarJovem() {
        String nome = nomeField.getText();
        String idade = idadeField.getText();
        String dadosDoJovem = dadosDoJovemArea.getText();

        if (!nome.isEmpty() && !idade.isEmpty()) {
            Jovem jovem = new Jovem();
            jovem.setNome(nome);
            jovem.setIdade(idade);
            jovem.setDadosDoJovem(dadosDoJovem);

            try {
                jovemDAO.salvar(jovem);
                JOptionPane.showMessageDialog(this, "Jovem adicionado com sucesso.");
                limparCampos();
                carregarJovens();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao adicionar o jovem.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Preencha o nome e a idade antes de adicionar.");
        }
    }

    private void atualizarJovem() {
        String nome = nomeField.getText();
        String idade = idadeField.getText();
        String dadosDoJovem = dadosDoJovemArea.getText();
        int selectedIndex = jovensTable.getSelectedRow();

        if (selectedIndex != -1) {
            try {
                List<Jovem> jovens = jovemDAO.listarTodos();
                Jovem jovem = jovens.get(selectedIndex);
                jovem.setNome(nome);
                jovem.setIdade(idade);
                jovem.setDadosDoJovem(dadosDoJovem);

                jovemDAO.atualizar(jovem);
                JOptionPane.showMessageDialog(this, "Jovem atualizado com sucesso.");
                limparCampos();
                carregarJovens();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar o jovem.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jovem para atualizar.");
        }
    }

    private void excluirJovem() {
        int selectedIndex = jovensTable.getSelectedRow();

        if (selectedIndex != -1) {
            try {
                List<Jovem> jovens = jovemDAO.listarTodos();
                Jovem jovem = jovens.get(selectedIndex);

                boolean removido = jovemDAO.excluir(jovem.getId());

                if (removido) {
                    JOptionPane.showMessageDialog(this, "Jovem removido com sucesso.");
                    limparCampos();
                    carregarJovens();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao remover o jovem.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao remover o jovem.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jovem para remover.");
        }
    }

    private void preencherCampos() {
        int selectedIndex = jovensTable.getSelectedRow();

        if (selectedIndex != -1) {
            try {
                List<Jovem> jovens = jovemDAO.listarTodos();
                Jovem jovem = jovens.get(selectedIndex);
                nomeField.setText(jovem.getNome());
                idadeField.setText(jovem.getIdade());
                dadosDoJovemArea.setText(jovem.getDadosDoJovem());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        idadeField.setText("");
        dadosDoJovemArea.setText("");
        jovensTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JovemGUI());
    }
}
