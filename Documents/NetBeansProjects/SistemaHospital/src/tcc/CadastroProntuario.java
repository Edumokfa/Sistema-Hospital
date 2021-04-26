package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Eduardo
 */
public class CadastroProntuario extends javax.swing.JInternalFrame implements ActionListener {

    String unidade;
    String classificacao;
    int IdPaciente;
    int IdMedico;
    int IdQuarto;
    static int IdMax;
    
    static Pacientes enviaTextoPacientes;

    public CadastroProntuario() {

        initComponents();
        TabelaPrescricao.getColumnModel().getColumn(0).setPreferredWidth(1);
        TabelaPrescricao.getColumnModel().getColumn(1).setPreferredWidth(200);
        TabelaPrescricao.getColumnModel().getColumn(2).setPreferredWidth(1);
        TabelaPrescricao.getColumnModel().getColumn(3).setPreferredWidth(1);
        TabelaPrescricao.getColumnModel().getColumn(4).setPreferredWidth(200);
        FormatarCampoCpf();
        Novo.addActionListener(this);
        Cadastrar.addActionListener(this);
        Alterar.addActionListener(this);
        Localizar.addActionListener(this);
        ctData.setText(getDateTime());
        ctHora.setText(getHourTime());

        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM prontuario");

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getHourTime() {
        DateFormat hora = new SimpleDateFormat("HH:mm");
        Date data = new Date();
        return hora.format(data);
    }

    private void FormatarCampoCpf() {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.install(ctCpf);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto.", "ERRO", JOptionPane.ERROR);
        }
    }

    class PosicaoCursor extends FocusAdapter {

        public void focusGained(FocusEvent e) {

            JTextComponent comp = (JTextComponent) e.getSource();
            comp.setCaretPosition(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Localizar) {
            try {
                String sql = "SELECT * FROM prontuario WHERE idProntuario= '" + ctId.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    atualizaCampos();
                    ctNome.requestFocus();
                    setBotoes(true, false, true, true);
                } else {
                    JOptionPane.showMessageDialog(null, "Prontuário não encontrado!");
                    ctNome.requestFocus();
                    BD.setResultSet("SELECT * FROM prontuario");
                }
            } catch (Exception erro) {

            }
        }

        if (e.getSource() == Novo) {
            setBotoes(true, true, false, true);
            limpaCampos();
            return;
        }

        if (e.getSource() == Cadastrar) {
            DefaultTableModel tabela = (DefaultTableModel) TabelaPrescricao.getModel();
            ArrayList Table = new ArrayList<>();
                    
            for (int i = 0; i < TabelaPrescricao.getRowCount(); i++) {

                String ObterTabela;
                ObterTabela = String.valueOf(tabela.getValueAt(i, 0));
                Table.add(ObterTabela);
            }
            String alta;
            if (Nao.isSelected() == true) {
                alta = "Nao";
                   if (enviaTextoPacientes == null) {
                    enviaTextoPacientes = new Pacientes();
                    enviaTextoPacientes.recebeProntuario(ctId.getText());
                } else {  
                    enviaTextoPacientes.recebeProntuario(ctId.getText());
                }
            } else {
                alta = "Sim";
            }

            String sql = "";
            sql = "INSERT INTO Prontuario(idProntuario, Pacientes_IdPaciente, Medicamentos, Medicos_IdMedico, DataInternacao, MotivoInternacao, HoraInternacao,Quarto_Numero, Alta_Paciente , DiagnosticoInternacao) VALUES ( '"
                    + ctId.getText() + "','"
                    + IdPaciente + "','"
                    + Table.toString() + "','"
                    + IdMedico + "','"
                    + ctData.getText() + "','"
                    + ctMotivo.getText() + "','"
                    + ctHora.getText() + "','"
                    + IdQuarto + "','"
                    + alta + "','"
                    + ctDiagnostico.getText() + "')";
            System.out.println(sql);
            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                setBotoes(true, false, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM Prontuario");

        }
        if (e.getSource() == Alterar) {
            DefaultTableModel tabela = (DefaultTableModel) TabelaPrescricao.getModel();
            ArrayList Table = new ArrayList<>();

            for (int i = 0; i < TabelaPrescricao.getRowCount(); i++) {

                String ObterTabela;
                ObterTabela = String.valueOf(tabela.getValueAt(i, 0));
                Table.add(ObterTabela);
            }
            String alta;
            if (Nao.isSelected() == true) {
                alta = "Nao";
                
            } else {
                alta = "Sim";
            }

            String sql = "";
            sql = "UPDATE prontuario SET "
                    + "idProntuario ='" + ctId.getText() + "',"
                    + "Pacientes_IdPaciente ='" + IdPaciente + "',"
                    + "Medicamentos ='" + Table.toString() + "',"
                    + "Medicos_IdMedico ='" + IdMedico + "',"
                    + "DataInternacao ='" + ctData.getText() + "',"
                    + "HoraInternacao ='" + ctHora.getText() + "',"
                    + "MotivoInternacao ='" + ctMotivo.getText() + "',"
                    + "Quarto_Numero ='" + ctNumeroQuarto.getText() + "',"
                    + "Alta_Paciente ='" + alta + "',"
                    + "DiagnosticoInternacao ='" + ctDiagnostico.getText() + "'"
                    + "WHERE idProntuario ='" + ctId.getText() + "'";
            System.out.println(sql);

            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                setBotoes(true, false, false, true);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM prontuario");
        }

    }

    public void limpaCampos() {

        ((DefaultTableModel) TabelaPrescricao.getModel()).setRowCount(0);

        ctNome.setText("");
        ctEndereco.setText("");
        ctBairro.setText("");
        ctCidade.setText("");
        ctRg.setText("");
        ctCpf.setText("");
        cbSexo.setSelectedItem("Feminino");
        ctCodigo.setText("");
        ctIntervaloDeTempo.setText("");
        ctNomeComercial.setText("");
        ctNomeQuimico.setText("");
        cbUnidade.setSelectedItem("Mg");
        cbClassificacao.setSelectedItem("Referência");
        ctNumeroQuarto.setText("");
        ctPavimento.setText("");
        ctMotivo.setText("");
        ctMedico.setText("");
        ctCrm.setText("");
        ctId.setText("");
        ctDiagnostico.setText("");
        Nao.setSelected(true);
        ctData.setText(getDateTime());
        ctHora.setText(getHourTime());
        ctNome.requestFocus();
    }

    public void limpaCamposMedicamentos() {
        ctCodigo.setText("");
        ctIntervaloDeTempo.setText("");
        ctNomeComercial.setText("");
        ctNomeQuimico.setText("");
        cbUnidade.setSelectedItem("Mg");
        cbClassificacao.setSelectedItem("Referência");
        ctNome.requestFocus();
    }

    public void atualizaCampos() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }

            ctId.setText(BD.resultSet.getString("idProntuario"));
            ctData.setText(BD.resultSet.getString("DataInternacao"));
            ctHora.setText(BD.resultSet.getString("HoraInternacao"));
            ctMotivo.setText(BD.resultSet.getString("MotivoInternacao"));
            ctDiagnostico.setText(BD.resultSet.getString("DiagnosticoInternacao"));
            if (BD.resultSet.getString("Alta_Paciente").equals("Nao")) {
                Nao.setSelected(true);
            } else {
                Sim.setSelected(true);
            }
            String Medicamentos = (BD.resultSet.getString("Medicamentos"));

            Medicamentos = Medicamentos.replace("[", "");
            Medicamentos = Medicamentos.replace("]", "");
            Medicamentos = Medicamentos.replace(" ", "");
            String[] teste = Medicamentos.split(Pattern.quote(","));
            DefaultTableModel tabela = (DefaultTableModel) TabelaPrescricao.getModel();

            try {
                String sql = "SELECT * FROM pacientes WHERE IdPaciente= '" + BD.resultSet.getString("Pacientes_IdPaciente") + "'";
                String sql2 = "SELECT * FROM medicos WHERE IdMedico= '" + BD.resultSet.getString("Medicos_IdMedico") + "'";
                String sql3 = "SELECT * FROM quarto WHERE Numero= '" + BD.resultSet.getString("Quarto_Numero") + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    IdPaciente = (BD.resultSet.getInt("IdPaciente"));
                    atualizaCamposPaciente();
                    ctId.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
                    ctId.requestFocus();
                    BD.setResultSet("SELECT * FROM pacientes");
                }
                BD.setResultSet(sql2);
                if (BD.resultSet.next()) {
                    IdMedico = (BD.resultSet.getInt("IdMedico"));
                    atualizaCamposMedico();
                    ctId.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Médico não encontrado!");
                    ctId.requestFocus();
                    BD.setResultSet("SELECT * FROM medicos");
                }
                BD.setResultSet(sql3);
                if (BD.resultSet.next()) {
                    IdQuarto = (BD.resultSet.getInt("Numero"));
                    atualizaCamposQuarto();
                    ctId.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Quarto não encontrado!");
                    ctId.requestFocus();
                    BD.setResultSet("SELECT * FROM quarto");
                }
                for (int i = 0; i != teste.length; i++) {
                    String verificarMedicamento = "";
                    if (verificarMedicamento != teste[i]) {
                        verificarMedicamento = teste[i];
                        String sql4 = "SELECT * FROM medicamentos WHERE idMedicamento= '" + teste[i] + "'";
                        BD.setResultSet(sql4);
                        if (BD.resultSet.next()) {
                            atualizaCamposMedicamento();
                            tabela.addRow(new String[]{ctCodigo.getText(), ctNomeComercial.getText(), unidade, ctIntervaloDeTempo.getText(), classificacao});

                            ctCodigo.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, "Medicamento não encontrado!");
                            ctCodigo.requestFocus();
                            BD.setResultSet("SELECT * FROM medicamentos");
                        }
                    } else {
                        tabela.addRow(new String[]{ctCodigo.getText(), ctNomeComercial.getText(), unidade, ctIntervaloDeTempo.getText(), classificacao});

                    }
                }

            } catch (Exception erro) {

            }

        } catch (SQLException erro) {

        }
    }

    public void atualizaCamposPaciente() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            ctNome.setText(BD.resultSet.getString("NomePaciente"));
            ctEndereco.setText(BD.resultSet.getString("EnderecoPaciente"));
            ctBairro.setText(BD.resultSet.getString("BairroPaciente"));
            ctCidade.setText(BD.resultSet.getString("CidadePaciente"));
            ctCpf.setText(BD.resultSet.getString("CpfPaciente"));
            ctRg.setText(BD.resultSet.getString("RgPaciente"));
            cbSexo.setSelectedItem(BD.resultSet.getString("SexoPaciente"));

        } catch (SQLException erro) {

        }
    }

    public void atualizaCamposMedico() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            ctMedico.setText(BD.resultSet.getString("NomeMedico"));
            ctCrm.setText(BD.resultSet.getString("CrmMedico"));

        } catch (SQLException erro) {

        }
    }

    public void atualizaCamposMedicamento() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            unidade = (BD.resultSet.getString("UnidadeMedicamento"));
            classificacao = (BD.resultSet.getString("ClassificacaoFarmacologica"));
            ctCodigo.setText(BD.resultSet.getString("idMedicamento"));
            cbUnidade.setSelectedItem(unidade);
            ctNomeComercial.setText(BD.resultSet.getString("NomeComercial"));
            ctNomeQuimico.setText(BD.resultSet.getString("NomeQuimico"));
            ctNomeComercial.setText(BD.resultSet.getString("NomeComercial"));
            cbClassificacao.setSelectedItem(BD.resultSet.getString("ClassificacaoFarmacologica"));
            ctIntervaloDeTempo.setText(BD.resultSet.getString("IntervaloDeTempo"));

        } catch (SQLException erro) {

        }
    }

    public void atualizaCamposQuarto() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            ctNumeroQuarto.setText(BD.resultSet.getString("Numero"));
            ctPavimento.setText(BD.resultSet.getString("PavimentoQuarto"));

        } catch (SQLException erro) {

        }
    }

    public void setBotoes(boolean bNovo, boolean bGravar, boolean bAlterar, boolean bLocalizar) {
        Novo.setEnabled(bNovo);
        Cadastrar.setEnabled(bGravar);
        Alterar.setEnabled(bAlterar);
        Localizar.setEnabled(bLocalizar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Alta = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        ctRg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ctNome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        ctCpf = new javax.swing.JFormattedTextField();
        ctCidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ctBairro = new javax.swing.JTextField();
        ctEndereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Cadastrar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ctMotivo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ctData = new javax.swing.JFormattedTextField();
        ctNumeroQuarto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ctPavimento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ctHora = new javax.swing.JFormattedTextField();
        ctMedico = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ctCrm = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaPrescricao = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ctNomeComercial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cbUnidade = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        ctCodigo = new javax.swing.JTextField();
        LocalizarMedicamento = new javax.swing.JButton();
        SalvarMedicamento = new javax.swing.JButton();
        ctNomeQuimico = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbClassificacao = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        ctIntervaloDeTempo = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ExcluirMedicamento = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        ctId = new javax.swing.JTextField();
        LimparCampos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ctDiagnostico = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        Nao = new javax.swing.JRadioButton();
        Sim = new javax.swing.JRadioButton();

        setClosable(true);
        setTitle("Cadastrar Prontuário Médico");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        jLabel7.setText("RG");

        ctRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctRgKeyTyped(evt);
            }
        });

        jLabel8.setText("CPF");

        ctNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctNomeMouseClicked(evt);
            }
        });
        ctNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctNomeKeyPressed(evt);
            }
        });

        jLabel9.setText("Sexo");

        jLabel2.setText("Nome Completo");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feminino", "Masculino" }));
        cbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexoActionPerformed(evt);
            }
        });

        jLabel3.setText("Cidade");

        ctCpf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctCpfMouseClicked(evt);
            }
        });
        ctCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctCpfKeyPressed(evt);
            }
        });

        ctCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctCidadeActionPerformed(evt);
            }
        });

        jLabel4.setText("Bairro");

        jLabel5.setText("Rua, Número");

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Novo.png"))); // NOI18N
        Novo.setText("Novo");
        Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoActionPerformed(evt);
            }
        });

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");
        Localizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalizarActionPerformed(evt);
            }
        });

        jLabel6.setText("Motivo da Internação");

        jLabel10.setText("Data da Internação");

        try {
            ctData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ctNumeroQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctNumeroQuartoActionPerformed(evt);
            }
        });
        ctNumeroQuarto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctNumeroQuartoKeyPressed(evt);
            }
        });

        jLabel11.setText("Número do quarto");

        jLabel12.setText("Pavimento");

        jLabel13.setText("Hora de internação");

        try {
            ctHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setText("Médico Responsável");

        jLabel15.setText("CRM");

        ctCrm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctCrmKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctCrmKeyTyped(evt);
            }
        });

        jLabel16.setText("Diagnóstico");

        jLabel17.setText("Prescrições");

        TabelaPrescricao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome do Remédio", "Unidade", "Horários", "Classificação Farmacológica"
            }
        ));
        jScrollPane1.setViewportView(TabelaPrescricao);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Inserir Medicamentos");

        jLabel18.setText("Nome Comercial");

        jLabel19.setText("Unidade");

        cbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mg", "Ml", "Comprimido" }));

        jLabel20.setText("Código");

        LocalizarMedicamento.setText("Localizar");
        LocalizarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalizarMedicamentoActionPerformed(evt);
            }
        });

        SalvarMedicamento.setText("Salvar");
        SalvarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarMedicamentoActionPerformed(evt);
            }
        });

        jLabel21.setText("Nome Químico");

        cbClassificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Referência", "Similar", "Genérico" }));

        jLabel22.setText("Classificação farmacológica");

        jLabel23.setText("Intervalo de tempo");

        ExcluirMedicamento.setText("Excluir");
        ExcluirMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirMedicamentoActionPerformed(evt);
            }
        });

        jLabel24.setText("IdProntuário");

        ctId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctIdKeyPressed(evt);
            }
        });

        LimparCampos.setText("Limpar Campos");
        LimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparCamposActionPerformed(evt);
            }
        });

        ctDiagnostico.setColumns(20);
        ctDiagnostico.setRows(5);
        jScrollPane2.setViewportView(ctDiagnostico);

        jLabel25.setText("O paciente recebeu alta:");

        Alta.add(Nao);
        Nao.setSelected(true);
        Nao.setText("Não");

        Alta.add(Sim);
        Sim.setText("Sim");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(ctBairro)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LocalizarMedicamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SalvarMedicamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ExcluirMedicamento))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ctNomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ctNomeQuimico)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(219, 219, 219)
                                .addComponent(LimparCampos))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(158, 158, 158)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ctMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ctCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ctNumeroQuarto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(ctId, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Sim)
                                    .addComponent(Nao)
                                    .addComponent(jLabel25))
                                .addGap(455, 455, 455)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ctMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(178, 178, 178)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ctData)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ctHora, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(150, 150, 150)
                                        .addComponent(jLabel19))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ctCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctIntervaloDeTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Localizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sim)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctNumeroQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ctMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ctData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ctHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctIntervaloDeTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctNomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctNomeQuimico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(LocalizarMedicamento)
                    .addComponent(SalvarMedicamento)
                    .addComponent(ExcluirMedicamento)
                    .addComponent(LimparCampos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ctRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRgKeyTyped
        if (ctRg.getText().length() >= 10) {
            evt.consume();
        }

    }//GEN-LAST:event_ctRgKeyTyped

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
       
    }//GEN-LAST:event_cbSexoActionPerformed

    private void ctCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctCidadeActionPerformed
        
    }//GEN-LAST:event_ctCidadeActionPerformed

    private void ctCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctCpfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql = "SELECT * FROM pacientes WHERE CpfPaciente= '" + ctCpf.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    IdPaciente = (BD.resultSet.getInt("IdPaciente"));
                    atualizaCamposPaciente();
                    ctNome.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
                    ctNome.requestFocus();
                    BD.setResultSet("SELECT * FROM pacientes");
                }
            } catch (Exception erro) {

            }
        }


    }//GEN-LAST:event_ctCpfKeyPressed

    private void ctNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctNomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql = "SELECT * FROM pacientes WHERE NomePaciente= '" + ctNome.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    IdPaciente = (BD.resultSet.getInt("IdPaciente"));
                    atualizaCamposPaciente();
                    ctNome.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                    ctNome.requestFocus();
                    BD.setResultSet("SELECT * FROM pacientes");
                }
            } catch (Exception erro) {

            }
        }


    }//GEN-LAST:event_ctNomeKeyPressed

    private void ctCrmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctCrmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql = "SELECT * FROM medicos WHERE CrmMedico= '" + ctCrm.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    IdMedico = (BD.resultSet.getInt("IdMedico"));
                    atualizaCamposMedico();
                    ctMedico.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Médico não encontrado!");
                    ctCrm.requestFocus();
                    BD.setResultSet("SELECT * FROM medicos");
                }
            } catch (Exception erro) {

            }
        }


    }//GEN-LAST:event_ctCrmKeyPressed

    private void ctIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctIdKeyPressed

    private void LocalizarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalizarMedicamentoActionPerformed
        try {
            String sql = "SELECT * FROM medicamentos WHERE idMedicamento= '" + ctCodigo.getText() + "'";
            BD.setResultSet(sql);
            if (BD.resultSet.next()) {
                atualizaCamposMedicamento();
                ctCodigo.requestFocus();
                setBotoes(true, true, true, true);
            } else {
                JOptionPane.showMessageDialog(null, "Medicamento não encontrado!");
                ctCodigo.requestFocus();
                BD.setResultSet("SELECT * FROM medicamentos");
            }
        } catch (Exception erro) {

        }
    }//GEN-LAST:event_LocalizarMedicamentoActionPerformed

    private void SalvarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarMedicamentoActionPerformed

        DefaultTableModel tabela = (DefaultTableModel) TabelaPrescricao.getModel();
        tabela.addRow(new String[]{ctCodigo.getText(), ctNomeComercial.getText(), unidade, ctIntervaloDeTempo.getText(), classificacao});

    }//GEN-LAST:event_SalvarMedicamentoActionPerformed

    private void LimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparCamposActionPerformed
        limpaCamposMedicamentos();
    }//GEN-LAST:event_LimparCamposActionPerformed

    private void ExcluirMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirMedicamentoActionPerformed
        if (TabelaPrescricao.getSelectedRow() >= 0) {
            DefaultTableModel tabela = (DefaultTableModel) TabelaPrescricao.getModel();
            tabela.removeRow(TabelaPrescricao.getSelectedRow());
            TabelaPrescricao.setModel(tabela);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione a linha a ser excluida");
        }
    }//GEN-LAST:event_ExcluirMedicamentoActionPerformed

    private void ctNumeroQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctNumeroQuartoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctNumeroQuartoActionPerformed

    private void ctNumeroQuartoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctNumeroQuartoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql = "SELECT * FROM quarto WHERE Numero= '" + ctNumeroQuarto.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    IdQuarto = (BD.resultSet.getInt("Numero"));
                    atualizaCamposQuarto();
                    ctMedico.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Quarto não encontrado!");
                    ctCrm.requestFocus();
                    BD.setResultSet("SELECT * FROM quarto");
                }
            } catch (Exception erro) {

            }
        }


    }//GEN-LAST:event_ctNumeroQuartoKeyPressed

    private void LocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalizarActionPerformed

    }//GEN-LAST:event_LocalizarActionPerformed

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
       enviaTextoPacientes = new Pacientes();
                    enviaTextoPacientes.recebeProntuario(ctId.getText());
    }//GEN-LAST:event_CadastrarActionPerformed

    private void NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoActionPerformed
        String sql = "";
            sql = "Select Max(idProntuario) from prontuario";
            System.out.println(sql);
            BD.setResultSet(sql);
            try {
                if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            IdMax = (Integer.parseInt(BD.resultSet.getString("Max(idProntuario)")) + 1);
             
            ctId.setText(String.valueOf(IdMax));
            }catch(SQLException erro){
                
                }
            
        ctId.setText(String.valueOf(IdMax));
    }//GEN-LAST:event_NovoActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
       
    }//GEN-LAST:event_formMouseDragged

    private void ctNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctNomeMouseClicked
        ctId.setText(String.valueOf(IdMax));
    }//GEN-LAST:event_ctNomeMouseClicked

    private void ctCpfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctCpfMouseClicked
        
        ctId.setText(String.valueOf(IdMax));
    }//GEN-LAST:event_ctCpfMouseClicked

    private void ctCrmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctCrmKeyTyped
        if(ctCrm.getText().length() >= 10){
            evt.consume();
        }
    }//GEN-LAST:event_ctCrmKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Alta;
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton ExcluirMedicamento;
    private javax.swing.JButton LimparCampos;
    private javax.swing.JButton Localizar;
    private javax.swing.JButton LocalizarMedicamento;
    private javax.swing.JRadioButton Nao;
    private javax.swing.JButton Novo;
    private javax.swing.JButton SalvarMedicamento;
    private javax.swing.JRadioButton Sim;
    private javax.swing.JTable TabelaPrescricao;
    private javax.swing.JComboBox<String> cbClassificacao;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JComboBox<String> cbUnidade;
    private javax.swing.JTextField ctBairro;
    private javax.swing.JTextField ctCidade;
    private javax.swing.JTextField ctCodigo;
    private javax.swing.JFormattedTextField ctCpf;
    private javax.swing.JTextField ctCrm;
    private javax.swing.JFormattedTextField ctData;
    private javax.swing.JTextArea ctDiagnostico;
    private javax.swing.JTextField ctEndereco;
    private javax.swing.JFormattedTextField ctHora;
    private javax.swing.JTextField ctId;
    private javax.swing.JTextField ctIntervaloDeTempo;
    private javax.swing.JTextField ctMedico;
    private javax.swing.JTextField ctMotivo;
    private javax.swing.JTextField ctNome;
    private javax.swing.JTextField ctNomeComercial;
    private javax.swing.JTextField ctNomeQuimico;
    private javax.swing.JTextField ctNumeroQuarto;
    private javax.swing.JTextField ctPavimento;
    private javax.swing.JTextField ctRg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
public void recebe(int recebe){
        IdMax = recebe;
        IdMax += 1;

}

    /**
     * @return the Tabela
     * 
     */
}
